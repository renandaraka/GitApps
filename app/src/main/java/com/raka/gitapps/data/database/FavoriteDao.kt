package com.raka.gitapps.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raka.gitapps.data.database.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: FavoriteEntity)

    @Delete
    fun delete(favorite: FavoriteEntity)

    @Query("SELECT * from user_favorite")
    fun getAllUser(): LiveData<List<FavoriteEntity>>

    @Query("SELECT * from user_favorite where username = :username")
    fun getUserFavorite(username: String): LiveData<FavoriteEntity>
}
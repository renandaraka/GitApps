package com.raka.gitapps.data.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.raka.gitapps.data.database.FavoriteEntity
import com.raka.gitapps.data.database.FavoriteRoomDatabase
import com.raka.gitapps.data.repository.FavoriteRepository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(FavoriteRoomDatabase.getDatabase(application).favoriteDao())

    val fav: LiveData<List<FavoriteEntity>> = mFavoriteRepository.getAllFavorite()

    fun tambah(favorite: FavoriteEntity){
        mFavoriteRepository.insert(favorite)
    }
    fun hapus(favorite: FavoriteEntity){
        mFavoriteRepository.delete(favorite)
    }

    fun getSpecificFavorite(username: String): LiveData<FavoriteEntity> = mFavoriteRepository.getSpecificUser(username)
}
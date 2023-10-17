package com.raka.gitapps.data.retrofit

import com.raka.gitapps.data.response.DetailUserResponse
import com.raka.gitapps.data.response.GithubResponse
import com.raka.gitapps.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser (
        @Query("q") query : String
    ): Call<GithubResponse>
    @GET("users/{username}")
    fun detailUser(
        @Path("username") username : String
    ): Call<DetailUserResponse>
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}
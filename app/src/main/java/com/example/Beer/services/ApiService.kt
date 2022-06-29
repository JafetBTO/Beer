package com.example.Beer.services


import com.example.Beer.model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")

    suspend fun getBeer(
        @Query("page") page : String,
        @Query("per_page") per_page : String) : RecyclerList

}
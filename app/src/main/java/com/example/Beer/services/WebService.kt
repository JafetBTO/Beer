package com.example.Beer.services


import com.example.Beer.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("")

    suspend fun getBeer(): Response<BeerResponse>
}
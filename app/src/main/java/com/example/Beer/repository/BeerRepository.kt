package com.example.Beer.repository


import com.example.Beer.services.RetrofitClient
import com.example.Beer.services.WebService

class BeerRepository {
    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    suspend fun getBeer() = apiService?.getBeer()
}
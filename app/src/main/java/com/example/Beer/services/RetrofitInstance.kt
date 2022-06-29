package com.example.Beer.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

   companion object  {

        fun getRtrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
package com.example.pruebacryptointento2.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AssetRetrofitClient {
    companion object {
        private const val BASE_URL = "https://api.coincap.io/v2/"

        fun retrofitInstance(): AssetRetrofitApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AssetRetrofitApi::class.java)
        }
    }
}
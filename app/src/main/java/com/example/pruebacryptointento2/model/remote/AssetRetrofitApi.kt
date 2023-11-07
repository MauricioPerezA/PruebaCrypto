package com.example.pruebacryptointento2.model.remote

import retrofit2.Response
import retrofit2.http.GET

interface AssetRetrofitApi {
    @GET("assets")
    suspend fun getData(): Response<Data>
}
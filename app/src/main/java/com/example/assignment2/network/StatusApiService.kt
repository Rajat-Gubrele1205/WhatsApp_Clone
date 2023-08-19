package com.example.assignment2.network


import ProductResponseData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    " https://www.inito.com/products/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface StatusApiService {
    @GET("list")
    suspend fun getProducts(): ProductResponseData
}

object StatusApi {
    val retrofitService : StatusApiService by lazy { // kotlin reflection
        retrofit.create( StatusApiService::class.java )
    }
}
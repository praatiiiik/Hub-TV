package com.example.hubtv.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Creating Retrofit Instance
 */
object Networking {
    private const val BASE_URL =  "https://miln.muvi.com/"
    fun createRetrofitInstance(): NetworkService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(NetworkService::class.java)
    }
}
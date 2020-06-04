package com.example.coffee.data.rijksMuseumApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MuseumApi {

        private val baseUrl = "https://www.rijksmuseum.nl/"
        fun createApi(): MuseumApiService {
            // Log de body van het request
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Build retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)  // Base Url
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // Add Gson as converter
                .build() // Build instance

            // Build the museum server api
            return retrofit.create(MuseumApiService::class.java)
    }
}
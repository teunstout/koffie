package com.example.koffie.data.rijksMuseumApi

import com.example.koffie.model.MuseumObject
import com.example.koffie.model.MuseumWebObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MuseumApiService {

    @GET("api/{language}/collection")
    fun museumObjects(
        @Path("language") language: String, // language of object
        @Query("key") apiKey: String, // The Api key of your account
        @Query("format") format: String, // Json format back
        @Query("imgonly") imgonly: Boolean, // Only objects with img
        @Query("p") pages: Int // page we want to have (default 1)
    ): Call<MuseumWebObject> // Retrofit gives back 1 object
}
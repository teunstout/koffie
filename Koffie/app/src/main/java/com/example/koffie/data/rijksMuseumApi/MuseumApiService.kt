package com.example.koffie.data.rijksMuseumApi

import com.example.koffie.model.MuseumObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MuseumApiService {
    @GET("/api/nl/collection?key={ApiKey}&format=json&&imgonly=true")
    fun museumObjects(
        @Path("ApiKey") apiKey: String
    ): Call<List<MuseumObject>>
}
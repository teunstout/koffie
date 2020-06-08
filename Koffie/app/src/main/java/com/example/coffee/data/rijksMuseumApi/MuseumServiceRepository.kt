package com.example.coffee.data.rijksMuseumApi

class MuseumServiceRepository {
    private val museumApi: MuseumApiService = MuseumApi().createApi()
    private val apiKey = "h8kfsPso" // Api key

    suspend fun getObjectsMuseum(page: Int) = museumApi.museumObjects("en" ,apiKey, "json", true, page)
}
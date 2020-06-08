package com.example.coffee.data.rijksMuseumApi

class MuseumServiceRepository {
    private val museumApi: MuseumApiService = MuseumApi().createApi()
    private val apiKey = "h8kfsPso" // Api key
    private val itemsPerPAge = 100 // 100 items per page

    suspend fun getObjectsMuseum(page: Int) = museumApi.museumObjects("en" ,apiKey, "json", true, itemsPerPAge, page)
}
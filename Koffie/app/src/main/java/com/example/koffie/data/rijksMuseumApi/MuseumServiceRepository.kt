package com.example.koffie.data.rijksMuseumApi

import retrofit2.Retrofit

class MuseumServiceRepository() {
    private val museumApi: MuseumApiService = MuseumApi().createApi()
    private val apiKey = "h8kfsPso"

    fun getObjectsMuseum(page: Int) = museumApi.museumObjects(apiKey, "json", true, page)
}
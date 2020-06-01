package com.example.coffee.model.rijksMuseumObjects

import com.google.gson.annotations.SerializedName

data class MuseumPictureUrl(
    @SerializedName("url") val url: String
)
package com.example.coffee.model.museum_model

import com.google.gson.annotations.SerializedName

/**
 * 3rd level from the request.
 */
data class MuseumPictureUrl(
    @SerializedName("url") val url: String
)
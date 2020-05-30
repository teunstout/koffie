package com.example.koffie.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MuseumObject(
    @SerializedName("webImage.url") val pictureUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("principalOrFirstMaker") val creator: String,
    @SerializedName("longTitle") val description: String
)
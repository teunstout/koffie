package com.example.koffie.model

import com.google.gson.annotations.SerializedName

data class MuseumObject(
    val pictureUrl: MuseumWebObject,
    @SerializedName("title") val title: String,
    @SerializedName("principalOrFirstMaker") val creator: String,
    @SerializedName("longTitle") val description: String
)
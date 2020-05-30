package com.example.coffee.model

import com.google.gson.annotations.SerializedName

data class MuseumObject(
    @SerializedName("webImage") val pictureUrl: MuseumPictureUrl,
    @SerializedName("title") val title: String,
    @SerializedName("principalOrFirstMaker") val creator: String,
    @SerializedName("longTitle") val description: String
)
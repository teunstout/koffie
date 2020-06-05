package com.example.coffee.model.museum_model

import com.google.gson.annotations.SerializedName

/**
 * 2nd level of the request
 */
data class MuseumArtifact(
    @SerializedName("webImage") val pictureUrl: MuseumPictureUrl,
    @SerializedName("title") val title: String,
    @SerializedName("principalOrFirstMaker") val creator: String,
    @SerializedName("longTitle") val description: String
)
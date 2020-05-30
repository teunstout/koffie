package com.example.coffee.model

import com.google.gson.annotations.SerializedName

data class MuseumWebObject(
    @SerializedName("artObjects") val artObjects: List<MuseumObject>
)
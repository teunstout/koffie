package com.example.coffee.model.rijksMuseumObjects

import com.example.coffee.model.rijksMuseumObjects.MuseumObject
import com.google.gson.annotations.SerializedName

data class MuseumWebObject(
    @SerializedName("artObjects") val artObjects: List<MuseumObject>
)
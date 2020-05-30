package com.example.koffie.model

import com.google.gson.annotations.SerializedName

data class MuseumWebObject(
    @SerializedName("artObjects") val artObjects: List<MuseumObject>
)
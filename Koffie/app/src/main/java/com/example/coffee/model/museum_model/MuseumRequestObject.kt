package com.example.coffee.model.museum_model

import com.google.gson.annotations.SerializedName

/**
 * 1st(top) level from the request.
 */
data class MuseumRequestObject(
    @SerializedName("artObjects") val artArtifacts: List<MuseumArtifact>
)
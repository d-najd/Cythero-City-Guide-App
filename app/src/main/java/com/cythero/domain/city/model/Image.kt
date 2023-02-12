package com.cythero.domain.city.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id") val id: Int,
    @SerializedName("path") val path: String,
    @SerializedName("cityId") val cityId: Int? = null,
)
package com.cythero.domain.city.model


import com.google.gson.annotations.SerializedName


data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("countryId") val countryId: Int,
    @SerializedName("locationId") val locationId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images") val images: List<Image> = emptyList(),
)
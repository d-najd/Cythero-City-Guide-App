package com.cythero.domain.review.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("id") val id: Long,
    @SerializedName("attractionId") val attractionId: Long,
    @SerializedName("stars") @androidx.annotation.IntRange(0, 9) val stars: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("username") val username: String,
) {
    companion object {
        fun fake(): Review = ReviewHolder.fake(1).data[0]
    }
}
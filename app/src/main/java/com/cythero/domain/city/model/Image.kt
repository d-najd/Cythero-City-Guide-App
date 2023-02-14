package com.cythero.domain.city.model


import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import com.google.gson.annotations.SerializedName

/**
 * @param drawable this value should be populated by the app
 */
data class Image(
    @SerializedName("id") val id: Int,
    @SerializedName("path") val path: String,
    @SerializedName("cityId") val cityId: Int? = null,
    @Transient val drawable: Drawable? = null,
)
package com.cythero.domain.attraction.model


import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("id") val id: Int,
    @SerializedName("flagPath") val flagPath: String,
    @SerializedName("address") val address: String,
    @Transient val flagPathDrawable: Drawable? = null,
): java.io.Serializable // TODO get rid of serializable

package com.cythero.domain.image_url.service

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import com.bumptech.glide.request.RequestOptions

interface ImageUrlRepository {

	suspend fun getOne(url: String, options: RequestOptions): Drawable?

}
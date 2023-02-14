package com.cythero.domain.image_url.interactor

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import com.bumptech.glide.request.RequestOptions
import com.cythero.domain.image_url.service.ImageUrlRepository

class GetImageByUrl(
	private val repository: ImageUrlRepository,
) {
	suspend fun subscribeOne(url: String, options: RequestOptions = RequestOptions()): Drawable? = repository.getOne(url, options)
}

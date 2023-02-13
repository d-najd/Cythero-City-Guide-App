package com.cythero.presentation.util

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cythero.util.launchIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** TODO add transient field to images which has the loaded image or null and the images should be a subscribe event */
suspend fun loadImage(context: Context, url: String) {
	val options = RequestOptions().fitCenter()
	val glide = Glide.with(context)
	withContext(Dispatchers.IO) {
		glide.asBitmap().load(url).error(Color.White).apply(options).submit().get()
	}
}

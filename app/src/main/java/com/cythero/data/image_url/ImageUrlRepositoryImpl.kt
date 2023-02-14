package com.cythero.data.image_url

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.cythero.cityguideapp.R
import com.cythero.domain.image_url.service.ImageUrlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.concurrent.ExecutionException

object ImageUrlRepositoryImpl: ImageUrlRepository {

	override suspend fun getOne(url: String, options: RequestOptions): Drawable? {
		return try {
			withContext(Dispatchers.IO) {
				Injekt.get<RequestManager>()
					.load(url)
					.apply(options)
					.submit()
					.get()
			}
		} catch(e: ExecutionException) {
			e.printStackTrace()
			null
		}
	}

}

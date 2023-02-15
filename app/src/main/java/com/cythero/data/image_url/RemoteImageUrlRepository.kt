package com.cythero.data.image_url

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.cythero.domain.image_url.service.ImageUrlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.concurrent.ExecutionException

object RemoteImageUrlRepository: ImageUrlRepository {

	override suspend fun getOne(url: String, options: RequestOptions): Drawable? {
		return try {
			/**
			 * TODO add preload ability which will load 1 or so pixels and create `theme color` using
			 * 	that, kind of like how firefox and other browsers do with preloading images in their
			 * 	image search
			 */
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

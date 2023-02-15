package com.cythero.data.image_url

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.cythero.cityguideapp.R
import com.cythero.domain.image_url.service.ImageUrlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.concurrent.ExecutionException

object FakeImageUrlRepository : ImageUrlRepository {

    override suspend fun getOne(url: String, options: RequestOptions): Drawable? {
        return try {
            withContext(Dispatchers.IO) {
                Injekt.get<RequestManager>()
                    .load(R.drawable.placeholder_city_1)
                    .submit()
                    .get()
            }
        } catch(e: ExecutionException) {
            e.printStackTrace()
            null
        }
    }

}

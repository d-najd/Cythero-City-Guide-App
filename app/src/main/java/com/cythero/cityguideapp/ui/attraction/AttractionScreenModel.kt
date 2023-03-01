package com.cythero.cityguideapp.ui.attraction

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.bumptech.glide.request.RequestOptions
import com.cythero.domain.attraction.interactor.GetAttraction
import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AttractionScreenModel(
	val id: Long,
	private val getAttraction: GetAttraction = Injekt.get(),
	private val getImageByUrl: GetImageByUrl = Injekt.get(),
): CityGuideStateScreenModel<AttractionScreenState>(AttractionScreenState.Loading) {
	private fun successState(): AttractionScreenState.Success = mutableState.value as AttractionScreenState.Success

	init {
		coroutineScope.launchIO {
			val attraction = getAttraction.awaitOne(id)!!
			val drawable = getImageByUrl.subscribeOne(attraction.location.flagPath)
			mutableState.update {
				AttractionScreenState.Success(
					attraction = attraction.copy(
						location = attraction.location.copy(
							flagPathDrawable = drawable
						)
					)
				)
			}
		}
	}
}

sealed class AttractionScreenState {

	@Immutable
	object Loading : AttractionScreenState()

	@Immutable
	data class Success(
		val attraction: Attraction,
	): AttractionScreenState()

}
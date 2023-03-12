package com.cythero.cityguideapp.ui.attraction

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.domain.attraction.interactor.GetAttraction
import com.cythero.domain.attraction.model.Attraction
import com.cythero.domain.image_url.interactor.GetImageByUrl
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AttractionScreenModel(
	private val id: Long,
	private val getAttraction: GetAttraction = Injekt.get(),
	private val getImageByUrl: GetImageByUrl = Injekt.get(),
): CityGuideStateScreenModel<AttractionScreenState>(AttractionScreenState.Loading) {
	private fun successState(): AttractionScreenState.Success = mutableState.value as AttractionScreenState.Success

	init {
		coroutineScope.launchIO {
			val attractionNoFlagPath = getAttraction.awaitOne(id)!!
			val drawable = getImageByUrl.subscribeOne(attractionNoFlagPath.location.flagPath)
			val attractionNoImages = attractionNoFlagPath.copy(
				location = attractionNoFlagPath.location.copy(
					flagPathDrawable = drawable
				)
			)
			mutableState.update {
				AttractionScreenState.Success(
					attractionNoImages
				)
			}
			fetchImages()
		}
	}

	/** must be launched for io scope */
	private suspend fun fetchImages() {
		for (imageDetails in successState().attraction.images) {
			val image = getImageByUrl.subscribeOne(imageDetails.path)
			val attraction = successState().attraction
			val images = attraction.images.toMutableList()
			images.add(
				imageDetails.copy(
					pathDrawable = image
				)
			)
			mutableState.update {
				successState().copy(
					attraction = attraction.copy(
						images = images
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
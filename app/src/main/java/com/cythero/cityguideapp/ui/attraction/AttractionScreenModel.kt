package com.cythero.cityguideapp.ui.attraction

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.domain.attraction.interactor.GetAttraction
import com.cythero.domain.attraction.model.Attraction
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class AttractionScreenModel(
	val id: Long,
	private val getAttraction: GetAttraction = Injekt.get(),
): CityGuideStateScreenModel<AttractionScreenState>(AttractionScreenState.Loading) {
	private fun successState(): AttractionScreenState.Success = mutableState.value as AttractionScreenState.Success

	init {
		coroutineScope.launchIO {
			getAttraction.awaitOne(id)?.let { attraction ->
				mutableState.update {
					AttractionScreenState.Success(
						attraction = attraction
					)
				}
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
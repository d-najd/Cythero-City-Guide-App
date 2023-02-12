package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.domain.city.interactor.GetCity
import com.cythero.domain.city.model.City
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.util.launchIO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class LibraryScreenModel(
	private val getCity: GetCity = Injekt.get(),
) : CityGuideStateScreenModel<LibraryScreenState>(LibraryScreenState.Loading) {
	init {
		coroutineScope.launchIO {
			mutableState.update {
				val cities = getCity.awaitAll()
				LibraryScreenState.Success(
					cities = cities
				)
			}
			/*
			sendEvent(Event.CanNotGetParentTable)
			sendEvent(Event.Test)
			// sendEvent(BaseEvent.Test)
			sendEvent(BaseEvent.LocalizedMessage(R.string.app_name))
			 */
		}
	}

	/*
    fun renameTable(id: Long, newName: String) {
        coroutineScope.launchIO {
            val tables = (mutableState.value as ProjectTableScreenState.Success).tables.toMutableList()
            tables.find { table -> table.id == id }!!.let { transientTable ->
                if(renameTable.await(id, newName)) {
                    // doing it this way so that state changes get updated for sure
                    mutableState.update {
                        tables.remove(transientTable)
                        tables.add(transientTable.copy(title = newName))
                        (mutableState.value as ProjectTableScreenState.Success).copy(
                            tables = tables
                        )
                    }
                }
            }
        }
    }
	 */

	/*
	sealed class Event: BaseEvent() {
		object Test: Event()
		object CanNotGetParentTable : LocalizedMessage(R.string.app_name)
	}
	 */
}

sealed class LibraryScreenState {

	@Immutable
	object Loading : LibraryScreenState()

	@Immutable
	data class Success(
		val cities: List<City>,
	): LibraryScreenState()

}

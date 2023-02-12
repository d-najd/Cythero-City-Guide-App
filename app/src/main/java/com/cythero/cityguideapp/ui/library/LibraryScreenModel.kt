package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.presentation.util.CityGuideStateScreenModel
import kotlinx.coroutines.launch

class LibraryScreenModel : CityGuideStateScreenModel<LibraryScreenState>(LibraryScreenState.Loading) {
	init {
		coroutineScope.launch {
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
		val test: String,
		/*
		val task: TableTask,
		val parentTable: ProjectTable,
		val sheet: TableTaskSheet? = null,
		 */
	): LibraryScreenState()

}

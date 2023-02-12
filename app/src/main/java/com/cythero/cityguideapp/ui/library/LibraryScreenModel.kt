package com.cythero.cityguideapp.ui.library

import android.content.Context
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.cityguideapp.R
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.presentation.util.event.BaseEvent
import kotlinx.coroutines.launch

class LibraryScreenModel(
	context: Context,
	taskId: Long,
) : CityGuideStateScreenModel<LibraryScreenState>(LibraryScreenState.Loading) {

	init {
		coroutineScope.launch {
			sendEvent(Event.CanNotGetParentTable)
			sendEvent(Event.Test)
			// sendEvent(BaseEvent.Test)
			sendEvent(BaseEvent.LocalizedMessage(R.string.app_name))
		}
	}

	sealed class Event: BaseEvent() {
		object Test: Event()
		object CanNotGetParentTable : LocalizedMessage(R.string.app_name)
	}
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

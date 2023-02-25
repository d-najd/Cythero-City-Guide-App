package com.cythero.presentation.util

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.cityguideapp.ui.base.event.BaseEvent
import com.cythero.cityguideapp.ui.base.event.EventSendable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class CityGuideStateScreenModel<S> (initialState: S): StateScreenModel<S>(initialState), EventSendable {
	private val _events: Channel<BaseEvent> = Channel(Int.MAX_VALUE)
	val events: Flow<BaseEvent> = _events.receiveAsFlow()

	override fun sendEvent(baseEvent: BaseEvent) {
		coroutineScope.launch {
			_events.send(baseEvent)
		}
	}
}



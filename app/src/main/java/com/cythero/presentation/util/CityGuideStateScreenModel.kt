package com.cythero.presentation.util

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.cythero.presentation.util.event.BaseEvent
import com.cythero.presentation.util.event.EventSendable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * TODO according to google passing context to screen-model is a bad idea, should find alternative
 */
abstract class CityGuideStateScreenModel<S> (initialState: S):
	StateScreenModel<S>(initialState = initialState), EventSendable {
	private val _events: Channel<BaseEvent> = Channel(Int.MAX_VALUE)
	val events: Flow<BaseEvent> = _events.receiveAsFlow()

	init {
		//Injekt.get<ContextHolder>().composeContext = context
		//Injekt.get<ContextHolder>().composeCoroutineScope = coroutineScope
	}

	override fun sendEvent(baseEvent: BaseEvent) {
		coroutineScope.launch {
			_events.send(baseEvent)
		}
	}
}



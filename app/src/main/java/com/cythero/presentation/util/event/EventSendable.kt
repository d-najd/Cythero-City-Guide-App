package com.cythero.presentation.util.event

@FunctionalInterface
interface EventSendable {
	fun sendEvent(baseEvent: BaseEvent)
}
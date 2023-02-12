package com.cythero.cityguideapp.ui.base.event

@FunctionalInterface
interface EventSendable {
	fun sendEvent(baseEvent: BaseEvent)
}
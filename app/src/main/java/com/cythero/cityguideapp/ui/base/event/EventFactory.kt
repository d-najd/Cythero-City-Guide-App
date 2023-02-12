package com.cythero.cityguideapp.ui.base.event

object EventFactory {
	fun getErrorMessageByCode(code: Int): BaseEvent {
		return when(code) {
			400 -> BaseEvent.BadRequest
			401 -> BaseEvent.Unauthorized
			403 -> BaseEvent.Forbidden
			404 -> BaseEvent.NotFound
			408 -> BaseEvent.RequestTimeout
			418 -> BaseEvent.IAmATeapot
			500 -> BaseEvent.InternalServerError
			501 -> BaseEvent.NotImplemented
			502 -> BaseEvent.BadGateway
			else -> BaseEvent.CustomMessage("Server Error code not handled $code")
		}
	}
}
package com.cythero.cityguideapp.ui.base.event

object EventSendableHolder {
	private lateinit var sendable: EventSendable

	@Synchronized
	fun set(sendable: EventSendable) {
		EventSendableHolder.sendable = sendable
	}

	/**
	 * @return the last registered sendable
	 * @throws UninitializedPropertyAccessException if a sendable has not been registered yet
	 */
	@Synchronized
	fun get(): EventSendable = sendable
}
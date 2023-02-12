package com.cythero.presentation.util.event

object EventSendableHolder {
	private lateinit var sendable: EventSendable

	@Synchronized
	fun register(sendable: EventSendable) {
		this.sendable = sendable
	}

	/**
	 * @return the last registered sendable
	 * @throws UninitializedPropertyAccessException if a sendable has not been registered yet
	 */
	@Synchronized
	fun get(): EventSendable = sendable
}
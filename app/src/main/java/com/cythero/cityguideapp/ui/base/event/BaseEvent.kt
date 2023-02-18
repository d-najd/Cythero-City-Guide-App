package com.cythero.cityguideapp.ui.base.event

import androidx.annotation.StringRes
import com.cythero.cityguideapp.R

open class BaseEvent {
	open class LocalizedMessage(@StringRes open val stringRes: Int): BaseEvent()
	open class LocalizedNetworkError(@StringRes override val stringRes: Int): LocalizedMessage(stringRes)
	open class CustomMessage(open val message: String): BaseEvent()
	open class CustomNetworkError(override val message: String): CustomMessage(message)

	object ConnectivityError: LocalizedNetworkError(R.string.error_connectivity)
	object BadRequest: LocalizedNetworkError(R.string.error_bad_request)
	object Unauthorized: LocalizedNetworkError(R.string.error_unauthorized)
	object Forbidden: LocalizedNetworkError(R.string.error_forbidden)
	object NotFound: LocalizedNetworkError(R.string.error_not_found)
	object RequestTimeout: LocalizedNetworkError(R.string.error_request_timeout)
	object IAmATeapot: LocalizedNetworkError(R.string.error_teapot)
	object InternalServerError: LocalizedNetworkError(R.string.error_internal_server_error)
	object NotImplemented: LocalizedNetworkError(R.string.error_not_implemented)
	object BadGateway: LocalizedNetworkError(R.string.bad_gateway)
}
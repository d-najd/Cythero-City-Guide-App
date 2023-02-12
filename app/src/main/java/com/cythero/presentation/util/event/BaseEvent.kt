package com.cythero.presentation.util.event

import androidx.annotation.StringRes
import com.cythero.cityguideapp.R

open class BaseEvent {
	open class LocalizedMessage(@StringRes val stringRes: Int): BaseEvent()
	open class CustomMessage(val message: String): BaseEvent()

	object NetworkError: LocalizedMessage(R.string.error_network)
	object BadRequest: LocalizedMessage(R.string.error_bad_request)
	object Unauthorized: LocalizedMessage(R.string.error_unauthorized)
	object Forbidden: LocalizedMessage(R.string.error_forbidden)
	object NotFound: LocalizedMessage(R.string.error_not_found)
	object RequestTimeout: LocalizedMessage(R.string.error_request_timeout)
	object IAmATeapot: LocalizedMessage(R.string.error_teapot)
	object InternalServerError: LocalizedMessage(R.string.error_internal_server_error)
	object NotImplemented: LocalizedMessage(R.string.error_not_implemented)
	object BadGateway: LocalizedMessage(R.string.bad_gateway)
}
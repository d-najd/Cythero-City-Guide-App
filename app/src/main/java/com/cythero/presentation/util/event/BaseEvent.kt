package com.cythero.presentation.util.event

import androidx.annotation.StringRes
import com.cythero.cityguideapp.R


open class BaseEvent {
	open class LocalizedMessage(@StringRes val stringRes: Int): BaseEvent()

	// object Test: LocalizedMessage(R.string.app_name)
}
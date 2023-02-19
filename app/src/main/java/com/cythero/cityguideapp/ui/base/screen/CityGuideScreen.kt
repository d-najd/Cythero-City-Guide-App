package com.cythero.cityguideapp.ui.base.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.cityguideapp.ui.base.event.BaseEvent
import com.cythero.cityguideapp.ui.base.event.EventSendableHolder
import com.cythero.util.toast
import kotlinx.coroutines.flow.collectLatest

/**
 * extension of [Screen] which includes event handling,
 * @param mainScreenModel the screen model which is considered as the `main`, this screen model will be registered as the
 * current event handler
 */
abstract class CityGuideScreen<T: CityGuideStateScreenModel<*>>(val mainScreenModel: T): Screen {

	/**
	 * @see ContentExtended()
	 */
	@Composable
	final override fun Content() {
		// the cast would have been useless if the entire program didn't crash without it
		@Suppress("USELESS_CAST")
		val screenModel = rememberScreenModel { mainScreenModel as CityGuideStateScreenModel<*> }
		EventHandler(screenModel)

		ContentExtended()
	}

	/**
	 * @see Content()
	 */
	@Composable abstract fun ContentExtended()

	/**
	 * Event handler needs to be explicitly called from the [Content()] otherwise the screen will not have event handling.
	 * if extended default behaviour is needed [DefaultEventHandler(screenModel)] can be called preferably as the first line.
	 * @param screenModel screen model
	 * @see DefaultEventHandler(screenModel)
	 * TODO throw error if the user forgets to add event handler
	 */
	@Composable
	protected open fun EventHandler(screenModel: CityGuideStateScreenModel<*>) {
		DefaultEventHandler(screenModel)
	}

	/**
	 * default event handler handles [BaseEvent.LocalizedMessage] and [BaseEvent.CustomMessage] by displaying toasts when
	 * either gets called, and handles event sendable holder
	 * @param screenModel screen model
	 * @see EventHandler(screenModel)
	 */
	@Composable
	protected fun DefaultEventHandler(screenModel: CityGuideStateScreenModel<*>) {
		EventSendableHolder.set(screenModel)
		val context = LocalContext.current
		LaunchedEffect(Unit) {
			screenModel.events.collectLatest { event ->
				if(event is BaseEvent.LocalizedMessage) {
					context.toast(event.stringRes)
				}
				else if(event is BaseEvent.CustomMessage) {
					context.toast(event.message)
				}
			}
		}
	}
}
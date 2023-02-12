package com.cythero.cityguideapp.ui.base.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.cityguideapp.ui.base.event.BaseEvent
import com.cythero.cityguideapp.ui.base.event.EventSendableHolder
import com.cythero.util.toast
import kotlinx.coroutines.flow.collectLatest

/**
 * extension of [Screen] which includes event handling,
 * The event handling needs to be explicitly called [EventHandler(screenModel)], otherwise the screen will have no error
 * handling and app instability including crashing will occur
 */
abstract class CityGuideScreen: Screen {

	/**
	 * Event handler needs to be explicitly called from the [Content()] otherwise the screen will not have event handling.
	 * if extended default behaviour is needed [DefaultEventHandler(screenModel)] can be called preferably as the first line.
	 * @param screenModel screen model
	 * @see DefaultEventHandler(screenModel)
	 * TODO throw error if the user forgets to add event handler
	 */
	@Composable
	protected open fun EventHandler(screenModel: CityGuideStateScreenModel<Any>) {
		DefaultEventHandler(screenModel)
	}

	/**
	 * default event handler handles [BaseEvent.LocalizedMessage] and [BaseEvent.CustomMessage] by displaying toasts when
	 * either gets called, and handles event sendable holder
	 * @param screenModel screen model
	 * @see EventHandler(screenModel)
	 */
	@Composable
	protected fun DefaultEventHandler(screenModel: CityGuideStateScreenModel<Any>) {
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
package com.cythero.cityguideapp.ui.attraction

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import cafe.adriel.voyager.navigator.Navigator
import com.cythero.cityguideapp.ui.base.controller.ComposeController

/**
 * @param attractionId must not be null
 */
class AttractionController(
	private val attractionId: Long
): ComposeController(Bundle.EMPTY) {

	/**
	 * useless constructor that should not be called and cannot be removed because compose
	 * @throws IllegalArgumentException when called
	 */
	@Suppress("unused")
	internal constructor(bundle: Bundle) : this(-1L)

	@Composable
	override fun ComposeContent() {
		if(attractionId == -1L) {
			throw IllegalArgumentException("attraction id is -1, this is probably caused due to calling the bundle constructor")
		}
		Navigator(screen = AttractionScreen(attractionId))
	}
}
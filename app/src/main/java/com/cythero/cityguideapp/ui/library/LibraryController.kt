package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.cythero.cityguideapp.ui.base.controller.ComposeController

class LibraryController : ComposeController() {

	@Composable
	override fun ComposeContent() {
		Navigator(screen = LibraryScreen())
	}

}

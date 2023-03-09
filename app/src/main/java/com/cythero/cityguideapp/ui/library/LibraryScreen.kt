package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bluelinelabs.conductor.asTransaction
import com.cythero.cityguideapp.ui.attraction.AttractionController
import com.cythero.cityguideapp.ui.base.screen.CityGuideScreen
import com.cythero.presentation.components.LoadingScreen
import com.cythero.presentation.library.LibraryScreenContent
import com.cythero.presentation.library.test.SignInScreen
import com.cythero.presentation.util.LocalRouter

class LibraryScreen : CityGuideScreen<LibraryScreenModel>(
	mainScreenModel = LibraryScreenModel(),
) {
	@Composable
	override fun ContentExtended() {
		val router = LocalRouter.currentOrThrow

		val state by mainScreenModel.state.collectAsState()
		if (state is LibraryScreenState.Loading){
			LoadingScreen()
			return
		}
		val successState = state as LibraryScreenState.Success

		SignInScreen()

		/*
		LibraryScreenContent(
			state = successState,
			onSortMenuClicked = mainScreenModel::invertSortMenu,
			onAttractionClicked = { router.pushController(AttractionController(it).asTransaction()) }
		)
		 */
	}
}
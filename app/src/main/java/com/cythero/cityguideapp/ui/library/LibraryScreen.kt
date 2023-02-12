package com.cythero.cityguideapp.ui.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import com.cythero.cityguideapp.ui.base.screen.CityGuideScreen
import com.cythero.presentation.util.CityGuideStateScreenModel

class LibraryScreen : CityGuideScreen() {

	@Composable
	override fun Content() {
		// val router = LocalRouter.currentOrThrow
		val screenModel = rememberScreenModel { LibraryScreenModel() }
		// the screen model implements the interface
		@Suppress("UNCHECKED_CAST")
		EventHandler(screenModel as CityGuideStateScreenModel<Any>)

		val state by screenModel.state.collectAsState()

		/*
		ProjectTableScreenContent(
			state = state,
			onBackClicked = router::popCurrentController,
			onMoveTableTasks = screenModel::moveTableTasks,
		 */
	}
}

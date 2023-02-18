package com.cythero.cityguideapp.ui.attraction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cythero.cityguideapp.ui.base.screen.CityGuideScreen
import com.cythero.presentation.attraction.AttractionScreenContent
import com.cythero.presentation.components.LoadingScreen
import com.cythero.presentation.util.CityGuideStateScreenModel
import com.cythero.presentation.util.LocalRouter

class AttractionScreen(
	private val attractionId: Long,
) : CityGuideScreen() {

	@Composable
	override fun Content() {
		val router = LocalRouter.currentOrThrow
		val screenModel = rememberScreenModel { AttractionScreenModel(id = attractionId) }
		// the screen model implements the interface
		@Suppress("UNCHECKED_CAST")
		EventHandler(screenModel as CityGuideStateScreenModel<Any>)

		val state by screenModel.state.collectAsState()
		if (state is AttractionScreenState.Loading){
			LoadingScreen()
			return
		}
		val successState = state as AttractionScreenState.Success

		AttractionScreenContent(
			state = successState,
			onBackClicked = router::popCurrentController,
		)
	}
}

package com.cythero.cityguideapp.ui.attraction

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import com.cythero.cityguideapp.ui.base.controller.ComposeController

class AttractionController: ComposeController {
	private constructor(attractionId: Long) : super(
		bundleOf(
			ATTRACTION_EXTRA to attractionId,
		)
	)

	// the constructor is necessary, removing it will result in a crash
	@Suppress("unused")
	constructor(bundle: Bundle) : this(
		bundle.getLong(ATTRACTION_EXTRA),
	)

	private val attractionId: Long = args.getLong(ATTRACTION_EXTRA)

	@Composable
	override fun ComposeContent() {
		// Navigator(screen = ProjectTableScreen(project))
	}

	companion object {
		private const val ATTRACTION_EXTRA = "attraction"
	}

}
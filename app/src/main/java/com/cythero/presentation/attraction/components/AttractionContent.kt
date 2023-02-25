package com.cythero.presentation.attraction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.cythero.cityguideapp.R
import com.cythero.cityguideapp.ui.attraction.AttractionScreenState
import com.cythero.domain.attraction.model.Attraction

/**
 * TODO check if this screen works on other devices
 * TODO this needs hella lot of refactoring
 */
@Composable
fun AttractionContent(
	state: AttractionScreenState.Success,
	paddingValues: PaddingValues,
) {
	val attraction = state.attraction
	BoxWithConstraints(
		modifier = Modifier
			.padding(paddingValues)
	) {
		// The background
		Image(
			bitmap = attraction.location.flagPathDrawable!!.toBitmap().asImageBitmap(),
			contentDescription = null,
			modifier = Modifier
				.fillMaxSize(),
			contentScale = ContentScale.Crop,
			alignment = Alignment.Center
		)

		val contentColor = Color.White
		AttractionForegroundContent(
			modifier = Modifier
				//.alpha(animatedVisibility)
				.fillMaxHeight()
				.padding(
					bottom = 12.dp,
					start = 24.dp,
					end = 24.dp,
				),
			attraction = attraction,
			contentColor = contentColor
		)
		AttractionScrollableContent(
			attraction = attraction,
			height = maxHeight,
		)
	}
}

/**
 * TODO make this more flexible in other words get rid of the secondScreenScrollState and instead calculate if
 *  250dp have been scrolled, since scrollState.value is not reliable something along the lines of the following
 *  formula should suffice
 *  { screenModel.maxValue - (composableHeight - (phoneScreenHeight + 250.dp)).toPercent() }
 *  NOTE the composable height and maxValue will change when the description will be expanded so beware of that
 */
@Composable
private fun AttractionScrollableContent(
	attraction: Attraction,
	height: Dp,
) {
	val screenScrollState = rememberScrollState()
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.verticalScroll(screenScrollState)
			.heightIn(min = height)
			.padding(top = height),
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colorScheme.background),
		) {
			val localDensity = LocalDensity.current

			// Create element height in dp state
			var columnHeightDp by remember {
				mutableStateOf(0.dp)
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.onGloballyPositioned { coordinates ->
						// Set column height using the LayoutCoordinates
						columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
					}
				,
			) {
				Image(
					bitmap = attraction.location.flagPathDrawable!!.toBitmap().asImageBitmap(),
					contentDescription = null,
					modifier = Modifier
						.height(350.dp)
						.fillMaxWidth(),
					contentScale = ContentScale.Crop,
					alignment = Alignment.Center
				)
				Column(
					modifier = Modifier
						.background(MaterialTheme.colorScheme.surface)
						.fillMaxWidth()
						.padding(
							top = 12.dp,
						)
				) {
					Column(
						modifier = Modifier
							.padding(horizontal = 20.dp),
					) {
						Row(
							verticalAlignment = Alignment.CenterVertically,
						) {
							AttractionTitleComposable(
								modifier = Modifier.padding(bottom = 12.dp),
								title = attraction.name,
							)
							AttractionRatingComposable(
								modifier = Modifier.fillMaxWidth(),
							)
						}

						AttractionAddressComposable(
							modifier = Modifier.padding(bottom = 26.dp),
							address = attraction.location.address
						)

						Text(
							text = attraction.description ?: stringResource(R.string.info_no_description_attraction),
						)
					}
				}
			}
			Column(
				modifier = Modifier
					.fillMaxWidth(),
				verticalArrangement = Arrangement.Bottom
			) {
				Button(
					modifier = Modifier
						.padding(top = maxOf(height - columnHeightDp, 20.dp) - 75.dp)
						.fillMaxWidth()
						.height(75.dp)
						.background(MaterialTheme.colorScheme.primary),
					onClick = { },
					shape = RoundedCornerShape(0.dp),
				) {
					Text(
						text = stringResource(R.string.field_get_directions),
					)
				}
			}
		}
	}
}

/*
	val animatedVisibility by animateFloatAsState(
		targetValue = when (screenScrollState.valueInPercent()) {
			in -10f..20f -> {
				1f
			}
			in 20f..110f -> {
				max(
					0f,
					(100f - screenScrollState.valueInPercent()) / 80f
				)
			}
			else -> {
				1f
			}
		},
	)
	 */
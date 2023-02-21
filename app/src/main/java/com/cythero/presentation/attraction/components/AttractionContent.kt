package com.cythero.presentation.attraction.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cythero.cityguideapp.R
import com.cythero.cityguideapp.ui.attraction.AttractionScreenState
import com.cythero.domain.attraction.model.Attraction
import com.cythero.presentation.components.CircularProgressBar
import com.cythero.presentation.components.CityGuideIconPairField
import com.cythero.presentation.util.valueInPercent
import kotlin.math.max

@Composable
fun AttractionContent(
	state: AttractionScreenState.Success,
	paddingValues: PaddingValues,
) {
	val attraction = state.attraction
	Box(
		modifier = Modifier
			.padding(paddingValues)
	) {
		Image(
			bitmap = attraction.location.flagPathDrawable!!.toBitmap().asImageBitmap(),
			contentDescription = null,
			modifier = Modifier
				.fillMaxSize(),
			contentScale = ContentScale.Crop,
			alignment = Alignment.Center
		)
		ContentBehindImage(
			state = state,
		)
	}
}

@Composable
private fun ContentBehindImage(
	state: AttractionScreenState.Success,
) {
	val contentColor = Color.White
	val attraction = state.attraction
	val firstScreenScrollState = rememberScrollState()
	val secondScreenScrollState = rememberScrollState()

	val animatedVisibility by animateFloatAsState(
		targetValue = when(firstScreenScrollState.valueInPercent()) {
			in -10f..20f -> {
				1f
			}
			in 20f..110f -> {
				max(0f, (100f - firstScreenScrollState.valueInPercent())/80f)
			}
			else  -> { 1f }
		},
	)
	Column(
		modifier = Modifier
			.verticalScroll(
				state = firstScreenScrollState,
			)
			.fillMaxSize()
		,
			/**
			 * TODO make this more flexible in other words get rid of the secondScreenScrollState and instead calculate if
			 *  250dp have been scrolled, since scrollState.value is not reliable something along the lines of the following
			 *  formula should suffice
			 *  { screenModel.maxValue - (composableHeight - (phoneScreenHeight + 250.dp)).toPercent() }
			 *  NOTE the composable height and maxValue will change when the description will be expanded so beware of that
			 *
			 */
		verticalArrangement = Arrangement.Bottom,
	) {
		Text(
			text = firstScreenScrollState.valueInPercent().toString() + " - " + secondScreenScrollState.valueInPercent()
				.toString()
		)
		FirstScreen(
			modifier = Modifier
				//.background(Color.Black.copy(.5f))
				.alpha(animatedVisibility)
				.imePadding()
				.height(LocalConfiguration.current.screenHeightDp.dp + 25.dp)
				.padding(
					bottom = 12.dp,
					start = 24.dp,
					end = 24.dp,
				),
			attraction = attraction,
			contentColor = contentColor
		)
		Column(
			modifier = Modifier
				.alpha(1f - animatedVisibility)
				.fillMaxSize()
				// .background(Color.Red.copy(.5f))
		) {
			Text(
				text = firstScreenScrollState.valueInPercent()
					.toString() + " - " + secondScreenScrollState.valueInPercent()
					.toString()
			)
			Image(
				bitmap = attraction.location.flagPathDrawable!!.toBitmap().asImageBitmap(),
				contentDescription = null,
				modifier = Modifier
					.height(300.dp)
					.fillMaxWidth(),
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
			Column(
				modifier = Modifier
					.background(MaterialTheme.colors.onSurface)
					.fillMaxWidth()
					.padding(
						top = 12.dp,
						start = 20.dp,
						end = 20.dp
					)
			) {
				Row {
					TitleComposable(title = attraction.name)
					RatingComposable(
						modifier = Modifier.fillMaxWidth()
					)
				}
			}
		}
	}
}

@Composable
private fun FirstScreen(
	modifier: Modifier = Modifier,
	attraction: Attraction,
	contentColor: Color,
) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Bottom,
	) {
		TitleComposable(
			title = attraction.name,
			contentColor = contentColor
		)

		AddressComposable(
			address = attraction.location.address,
			contentColor = contentColor,
		)

		Divider(
			thickness = .6.dp,
			color = contentColor.copy(.5f),
			modifier = Modifier
				.width(75.dp)
				.padding(bottom = 6.dp),
		)
		Row(
			verticalAlignment = Alignment.CenterVertically,
		) {
			PricePerNightComposable(contentColor = contentColor)

			RatingComposable(
				modifier = Modifier.fillMaxWidth(),
				contentColor = contentColor
			)
		}
	}
}

@Composable
private fun TitleComposable(
	title: String,
	contentColor: Color = Color.Unspecified,
) {
	Text(
		modifier = Modifier.padding(bottom = 12.dp),
		color = contentColor,
		text = title,
		fontWeight = FontWeight.Bold,
		fontSize = 52.sp,
		letterSpacing = 5.sp,
	)
}

@Composable
private fun AddressComposable(
	address: String,
	contentColor: Color,
) {
	CityGuideIconPairField(
		modifier = Modifier.padding(bottom = 26.dp),
		iconContent = {
			Icon(
				tint = contentColor,
				imageVector = Icons.Sharp.LocationOn,
				contentDescription = null,
			)
		},
		textContent = {
			Text(
				text = address,
				fontWeight = FontWeight.ExtraLight,
				color = contentColor,
			)
		}
	)
}

@Composable
private fun PricePerNightComposable(
	contentColor: Color,
) {
	Row {
		Text(
			color = contentColor,
			text = stringResource(R.string.field_from),
			fontWeight = FontWeight.ExtraLight,
		)
		Text(
			text = " $1,289 ",
			fontWeight = FontWeight.ExtraLight,
			color = MaterialTheme.colors.primary,
		)
		Text(
			color = contentColor,
			text = stringResource(R.string.field_for_week),
			fontWeight = FontWeight.ExtraLight,
		)
	}
}

@Composable
private fun RatingComposable(
	modifier: Modifier = Modifier,
	contentColor: Color = Color.Unspecified,
){
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.End,
	) {
		Box(
			modifier = Modifier.size(75.dp),
			contentAlignment = Alignment.Center,
		) {
			val rating = 6.52f
			CircularProgressBar(
				startAngle = 180f,
				sweepAngle = 360f,
				strokeWidth = 4.dp,
				modifier = Modifier.size(75.dp),
				percentage = rating/10f,
				endIndicatorEnabled = false,
			)
			Text(
				text = rating.toString(),
				color = contentColor,
			)
		}
	}
}
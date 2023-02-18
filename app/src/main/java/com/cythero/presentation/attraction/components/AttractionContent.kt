package com.cythero.presentation.attraction.components

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cythero.cityguideapp.R
import com.cythero.cityguideapp.ui.attraction.AttractionScreenState
import com.cythero.presentation.components.CityGuideIconPairField

@Composable
fun AttractionContent(
	state: AttractionScreenState.Success,
	paddingValues: PaddingValues,
) {
	val attraction = state.attraction
	Box {
		attraction.location.flagPathDrawable?.let {
			Image(
				bitmap = it.toBitmap().asImageBitmap(),
				contentDescription = null,
				modifier = Modifier
					.fillMaxSize(),
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center
			)
		}
		ContentBehindImage(state)
	}
}

@Composable
private fun ContentBehindImage(
	state: AttractionScreenState.Success,
) {
	val attraction = state.attraction
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(
				horizontal = 12.dp,
				vertical = 18.dp
			),
		verticalArrangement = Arrangement.Bottom,
	) {
		Row {
			Text(
				text = stringResource(R.string.field_from),
				fontWeight = FontWeight.Light,
			)
			Text(
				text = " $1,289 ",
				fontWeight = FontWeight.Light,
				color = MaterialTheme.colors.primary,
			)
			Text(
				text = stringResource(R.string.field_for_week),
				fontWeight = FontWeight.Light,
			)
		}
		
		Divider(
			modifier = Modifier
				.width(50.dp)
				.padding(vertical = 12.dp),
		)
		
		CityGuideIconPairField(
			iconContent = {
				Icon(
					imageVector = Icons.Rounded.LocationOn,
					contentDescription = null,
				)
			},
			text = attraction.location.address,
		)

		Text(
			text = attraction.location.address,
			fontWeight = FontWeight.SemiBold,
			fontSize = 20.sp,
			letterSpacing = 4.sp,
		)
	}
}
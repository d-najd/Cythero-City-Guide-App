package com.cythero.presentation.attraction.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.R
import com.cythero.domain.attraction.model.Attraction

@Composable
fun AttractionScrollableTopContent(
	modifier: Modifier,
	spacerHeight: Dp,
	attraction: Attraction,
) {
	Column(
		modifier = modifier
	) {
		BoxWithConstraints {
			val maxBoxWidth = maxWidth
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.padding(bottom = 12.dp)
			) {
				AttractionTitleComposable(
					modifier = Modifier
						.widthIn(max = maxBoxWidth - 77.5.dp),
					title = attraction.name,
				)
				AttractionRatingComposable(
					modifier = Modifier.fillMaxWidth()
				)
			}
		}
		AttractionAddressComposable(
			modifier = Modifier.padding(bottom = 26.dp),
			address = attraction.location.address
		)
		Text(
			text = attraction.description
				?: stringResource(R.string.info_no_description_attraction),
		)
		Spacer(modifier = Modifier.height(spacerHeight))
	}
}
package com.cythero.presentation.attraction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cythero.cityguideapp.R
import com.cythero.domain.attraction.model.Attraction

@Composable
fun AttractionScrollableTopContent(
	attraction: Attraction,
) {
	Column(
		modifier = Modifier
			.background(MaterialTheme.colorScheme.surface)
			.fillMaxWidth()
			.padding(
				top = 12.dp, start = 20.dp, end = 20.dp
			)
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
			text = attraction.description
				?: stringResource(R.string.info_no_description_attraction),
		)
	}
}
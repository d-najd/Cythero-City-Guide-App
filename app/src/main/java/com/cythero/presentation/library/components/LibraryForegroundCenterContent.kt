package com.cythero.presentation.library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cythero.domain.attraction.model.Attraction

@Composable
fun LibraryForegroundCenterContent(
	attraction: Attraction,
	themeColor: Color,
	contentColor: Color,
	paddingCentered: Dp,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(bottom = paddingCentered / 2f),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Text(
			modifier = Modifier.padding(horizontal = 16.dp),
			text = attraction.name.uppercase(),
			fontFamily = FontFamily.SansSerif,
			fontWeight = FontWeight.SemiBold,
			color = contentColor,
			letterSpacing = 3.25.sp,
			textAlign = TextAlign.Center,
			fontSize = 24.sp,
			maxLines = 2,
			lineHeight = 30.sp,
		)
		Button(
			colors = ButtonDefaults.buttonColors(
				disabledContainerColor = themeColor.copy(.9f),
				disabledContentColor = contentColor,
			),
			shape = RoundedCornerShape(24.dp),
			enabled = false,
			onClick = { /*TODO*/ }
		) {
			Text(
				text = "2,642 VISITORS",
				fontFamily = FontFamily.SansSerif,
				fontWeight = FontWeight.ExtraLight,
				fontSize = 15.sp,
			)
		}
	}
}
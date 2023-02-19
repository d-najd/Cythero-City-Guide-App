package com.cythero.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * TODO colors don't seem to follow dark theme colors
 */
@Composable
fun CircularProgressBar(
	modifier: Modifier = Modifier,
	startAngle: Float = 140f,
	sweepAngle: Float = 260f,
	percentage: Float,
	fillColor: Color = MaterialTheme.colors.primary,
	backgroundColor: Color = MaterialTheme.colors.background.copy(.7f),
	strokeWidth: Dp = 10.dp,
	endIndicatorColor: Color = Color.White,
	endIndicatorRadius: Float = 5F,
	endIndicatorEnabled: Boolean = true,
) {
	Canvas(
		modifier = modifier,
	) {
		// Background Line
		drawArc(
			color = backgroundColor,
			startAngle,
			sweepAngle,
			false,
			style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
			size = Size(size.width, size.height)
		)

		drawArc(
			color = fillColor,
			startAngle,
			percentage * sweepAngle,
			false,
			style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
			size = Size(size.width, size.height)
		)

		if(endIndicatorEnabled) {
			val angleInDegrees = (percentage * sweepAngle) + 50.0
			val radius = (size.height / 2)
			val x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
			val y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)

			drawCircle(
				color = endIndicatorColor,
				radius = endIndicatorRadius,
				center = Offset(x, y)
			)
		}
	}
}

@Preview
@Composable
fun PreviewCircularProgressBar() {
	CircularProgressBar(
		modifier = Modifier.size(150.dp).padding(10.dp),
		percentage = 0.80f,
	)
}
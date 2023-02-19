package com.cythero.presentation.util

import androidx.compose.foundation.ScrollState

fun ScrollState.valueInPercent(): Float = (this.value.toFloat() / (this.maxValue.toFloat() -1))*100


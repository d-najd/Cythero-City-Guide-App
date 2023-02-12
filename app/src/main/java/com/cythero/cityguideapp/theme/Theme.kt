package com.cythero.cityguideapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = cityguide_theme_light_primary,
    onPrimary = cityguide_theme_light_onPrimary,
    primaryContainer = cityguide_theme_light_primaryContainer,
    onPrimaryContainer = cityguide_theme_light_onPrimaryContainer,
    secondary = cityguide_theme_light_secondary,
    onSecondary = cityguide_theme_light_onSecondary,
    secondaryContainer = cityguide_theme_light_secondaryContainer,
    onSecondaryContainer = cityguide_theme_light_onSecondaryContainer,
    tertiary = cityguide_theme_light_tertiary,
    onTertiary = cityguide_theme_light_onTertiary,
    tertiaryContainer = cityguide_theme_light_tertiaryContainer,
    onTertiaryContainer = cityguide_theme_light_onTertiaryContainer,
    error = cityguide_theme_light_error,
    onError = cityguide_theme_light_onError,
    errorContainer = cityguide_theme_light_errorContainer,
    onErrorContainer = cityguide_theme_light_onErrorContainer,
    outline = cityguide_theme_light_outline,
    background = cityguide_theme_light_background,
    onBackground = cityguide_theme_light_onBackground,
    surface = cityguide_theme_light_surface,
    onSurface = cityguide_theme_light_onSurface,
    surfaceVariant = cityguide_theme_light_surfaceVariant,
    onSurfaceVariant = cityguide_theme_light_onSurfaceVariant,
    inverseSurface = cityguide_theme_light_inverseSurface,
    inverseOnSurface = cityguide_theme_light_inverseOnSurface,
    inversePrimary = cityguide_theme_light_inversePrimary,
    surfaceTint = cityguide_theme_light_surfaceTint,
    outlineVariant = cityguide_theme_light_outlineVariant,
    scrim = cityguide_theme_light_scrim,
)

private val DarkColors = darkColorScheme(
    primary = cityguide_theme_dark_primary,
    onPrimary = cityguide_theme_dark_onPrimary,
    primaryContainer = cityguide_theme_dark_primaryContainer,
    onPrimaryContainer = cityguide_theme_dark_onPrimaryContainer,
    secondary = cityguide_theme_dark_secondary,
    onSecondary = cityguide_theme_dark_onSecondary,
    secondaryContainer = cityguide_theme_dark_secondaryContainer,
    onSecondaryContainer = cityguide_theme_dark_onSecondaryContainer,
    tertiary = cityguide_theme_dark_tertiary,
    onTertiary = cityguide_theme_dark_onTertiary,
    tertiaryContainer = cityguide_theme_dark_tertiaryContainer,
    onTertiaryContainer = cityguide_theme_dark_onTertiaryContainer,
    error = cityguide_theme_dark_error,
    onError = cityguide_theme_dark_onError,
    errorContainer = cityguide_theme_dark_errorContainer,
    onErrorContainer = cityguide_theme_dark_onErrorContainer,
    outline = cityguide_theme_dark_outline,
    background = cityguide_theme_dark_background,
    onBackground = cityguide_theme_dark_onBackground,
    surface = cityguide_theme_dark_surface,
    onSurface = cityguide_theme_dark_onSurface,
    surfaceVariant = cityguide_theme_dark_surfaceVariant,
    onSurfaceVariant = cityguide_theme_dark_onSurfaceVariant,
    inverseSurface = cityguide_theme_dark_inverseSurface,
    inverseOnSurface = cityguide_theme_dark_inverseOnSurface,
    inversePrimary = cityguide_theme_dark_inversePrimary,
    surfaceTint = cityguide_theme_dark_surfaceTint,
    outlineVariant = cityguide_theme_dark_outlineVariant,
    scrim = cityguide_theme_dark_scrim,
)


@Composable
fun CityGuideTheme(content: @Composable () -> Unit) {
    val colors = if (!isSystemInDarkTheme()) {
        LightColors
    } else {
        DarkColors
    }
    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}

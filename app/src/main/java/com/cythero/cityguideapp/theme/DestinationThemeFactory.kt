package com.cythero.cityguideapp.theme

import androidx.compose.ui.graphics.Color

object DestinationThemeFactory {
	private val colors = listOf(
		cityguide_color_clue_ribbon,
		cityguide_color_azure_radiance,
		cityguide_color_pistachio,
		cityguide_color_all_ports,
		cityguide_color_razzmatazz,
		cityguide_color_turbo,
		cityguide_color_california,
		cityguide_color_fuego,
		cityguide_color_niagara,
		cityguide_color_amethyst,
		cityguide_color_rio_grande,
		cityguide_color_roof_terracotta,
		cityguide_color_gold,
		cityguide_color_aqua,
		cityguide_color_orange_peel,
	)

	fun getRandomColor(): Color = colors.random()
}
package com.example.miso.ui.theme

import androidx.compose.runtime.Composable
import com.example.miso.ui.theme.color.Color

@Composable
fun SMSTheme(
    colors: ColorTheme = if (true) Color else Color,
    typography: MisoTypography = MisoTypography,
    content: @Composable (colors: ColorTheme, typography: MisoTypography) -> Unit
) {
    content(colors, typography)
}
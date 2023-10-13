package com.example.miso.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.miso.ui.theme.color.LightColor

@Composable
fun MisoTheme(
    colors: ColorTheme = if (true) LightColor else LightColor,
    typography: MisoTypography = MisoTypography,
    content: @Composable (colors: ColorTheme, typography: MisoTypography) -> Unit
) {
    content(colors, typography)
    val darkTheme = isSystemInDarkTheme()
    val view = LocalView.current
    SideEffect {
        with(view.context as Activity) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
        }
    }
}
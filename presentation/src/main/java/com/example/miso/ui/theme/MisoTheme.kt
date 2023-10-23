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
    val darkTheme = isSystemInDarkTheme()
    content(colors, typography)
    val view = LocalView.current
    SideEffect {
        val context = view.context
        if (context is Activity) {
            WindowCompat.setDecorFitsSystemWindows(context.window, false)
            val insetsController = WindowCompat.getInsetsController(context.window, view)
            insetsController.isAppearanceLightStatusBars = darkTheme
            context.window.statusBarColor = Color.Transparent.toArgb()
            context.window.navigationBarColor = Color.Transparent.toArgb()
        }
    }
}
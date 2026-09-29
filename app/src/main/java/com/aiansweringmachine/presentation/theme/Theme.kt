package com.aiansweringmachine.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = ElectricBlue,
    background = SurfaceLight,
    surface = SurfaceLight
)

private val DarkColors = darkColorScheme(
    primary = SoftBlue,
    background = DeepNavy,
    surface = DeepNavy
)

@Composable
fun AiAnsweringMachineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}

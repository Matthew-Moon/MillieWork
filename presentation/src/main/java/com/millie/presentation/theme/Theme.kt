package com.millie.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


 private val LightColorScheme = lightColorScheme(
    background = MillieBackground,
    surface = MillieBackground,
    primary = Color.White,
    secondary = Color.White,
    tertiary = Color.White


)

@Composable
fun MillieApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}

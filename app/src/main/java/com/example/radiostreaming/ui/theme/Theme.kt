package com.example.radiostreaming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RadioAccent,
    secondary = RadioSecondary,
    tertiary = RadioAccentLight,
    background = RadioDarkBackground,
    surface = RadioDarkSurface,
    surfaceVariant = RadioDarkSurfaceVariant,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextWhite,
    onSurface = TextWhite
)

@Composable
fun RadioStreamingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Defaulting to sleek dark audio theme
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}

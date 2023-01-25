package com.psablik.bikemarket.presentation.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val colorSchem: ColorScheme = ColorScheme(
    primary = Color.Red,
    onPrimary = Color.Red,
    onPrimaryContainer = Color.Red,
    primaryContainer = Color.Red,
    inversePrimary = Color.Red,
    secondary = Color.Red,
    onSecondary = Color.Red,
    secondaryContainer = Color.Red,
    onSecondaryContainer = Color.Red,
    tertiary = Color.Red,
    onTertiary = Color.Red,
    tertiaryContainer = Color.Red,
    onTertiaryContainer = Color.Red,
    background = Color.Red,
    onBackground = Color.Red,
    surface = Color.Red,
    onSurface = Color.Red,
    surfaceVariant = Color.Red,
    onSurfaceVariant = Color.Red,
    surfaceTint = Color.Red,
    inverseSurface = Color.Red,
    inverseOnSurface = Color.Red,
    error = Color.Red,
    onError = Color.Red,
    errorContainer = Color.Red,
    onErrorContainer = Color.Red,
    outline = Color.Red,
    outlineVariant = Color.Red,
    scrim = Color.Red,
)

@Composable
fun BikeMarketTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = colorSchem,
            typography = Typography,
            content = content
        )
    }
}
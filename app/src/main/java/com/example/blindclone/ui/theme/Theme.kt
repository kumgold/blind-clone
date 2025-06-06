package com.example.blindclone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// 1. 라이트 모드 색상 조합 정의
private val LightColorScheme = lightColorScheme(
    primary = OceanBlue,
    onPrimary = OnOceanBlue,
    primaryContainer = OceanBlueContainer,
    onPrimaryContainer = OnOceanBlueContainer,
    secondary = SeafoamGreen,
    onSecondary = OnSeafoamGreen,
    secondaryContainer = SeafoamGreenContainer,
    onSecondaryContainer = OnSeafoamGreenContainer,
    tertiary = SoftCoral,
    onTertiary = OnSoftCoral,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnBackgroundLight,
    inverseOnSurface = InverseOnSurfaceLight
)

// 2. 다크 모드 색상 조합 정의
private val DarkColorScheme = darkColorScheme(
    primary = OceanBlueDark,
    onPrimary = OnOceanBlueDark,
    primaryContainer = OceanBlueContainerDark,
    onPrimaryContainer = OnOceanBlueContainerDark,
    secondary = SeafoamGreenDark,
    onSecondary = OnSeafoamGreenDark,
    secondaryContainer = SeafoamGreenContainerDark,
    onSecondaryContainer = OnSeafoamGreenContainerDark,
    tertiary = SoftCoralDark,
    onTertiary = OnSoftCoralDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnBackgroundDark,
    inverseOnSurface = InverseOnSurfaceDark
)

@Composable
fun BlindCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
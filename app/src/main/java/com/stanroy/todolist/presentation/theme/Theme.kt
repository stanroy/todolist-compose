package com.stanroy.todolist.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Beige,
    primaryVariant = BeigeVariant,
    secondary = Gray,
    onPrimary = Black,
    background = Black,
    onBackground = Beige
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = BlackVariant,
    secondary = Gray,
    onPrimary = Beige,
    background = Beige,
    onBackground = Black
)

@Composable
fun TodolistTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = TodoAppTypography.typography,
        shapes = Shapes,
        content = content
    )
}
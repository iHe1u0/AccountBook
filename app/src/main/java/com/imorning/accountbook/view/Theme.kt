package com.imorning.accountbook.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

val overviewColors = lightColors(
    primary = Color.Black,
    surface = Color.White,
    secondary = Color.DarkGray,
)

@Composable
fun OverviewTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = overviewColors,
    ) {
        Surface(
            // modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
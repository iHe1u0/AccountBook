package com.imorning.accountbook.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imorning.accountbook.view.Colors.overviewThemeColors

@Composable
fun OverviewTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = overviewThemeColors,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
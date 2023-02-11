package com.psablik.bikemarket.presentation.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.psablik.bikemarket.ui.theme.Primary

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(color = Primary, modifier = modifier)
}
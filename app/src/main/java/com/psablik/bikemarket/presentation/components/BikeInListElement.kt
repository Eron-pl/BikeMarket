package com.psablik.bikemarket.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psablik.bikemarket.presentation.ui.theme.spacing

@Composable
fun BikeInListElement(
    bikeModelName: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.xs),
        shadowElevation = MaterialTheme.spacing.xxs
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(175.dp) // Todo: Ratio of img or extract to dimens
                .padding(MaterialTheme.spacing.s)
        ) {
            Text(text = bikeModelName)
        }
    }
}

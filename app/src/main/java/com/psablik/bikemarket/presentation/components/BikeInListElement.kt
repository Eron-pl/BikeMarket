package com.psablik.bikemarket.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import com.psablik.bikemarket.presentation.ui.theme.B1
import com.psablik.bikemarket.presentation.ui.theme.H4
import com.psablik.bikemarket.presentation.ui.theme.spacing

@Composable
fun BikeInListElement(
    bikeModelName: String,
    bikeImgPath: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.xs),
        shadowElevation = MaterialTheme.spacing.xxxs,
        color = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1.2f)
                .padding(MaterialTheme.spacing.s)
        ) {
            Image(painter = rememberAsyncImagePainter(bikeImgPath),
                contentDescription = "Bike image" // Todo: strings
            )

            Spacer(Modifier.height(MaterialTheme.spacing.s))

            Text(text = bikeModelName, style = B1)
        }
    }
}

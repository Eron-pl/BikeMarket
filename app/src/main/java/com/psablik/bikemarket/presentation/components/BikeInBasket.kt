package com.psablik.bikemarket.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.psablik.bikemarket.presentation.ui.theme.B3
import com.psablik.bikemarket.presentation.ui.theme.Info
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Primary

@Composable
fun BikeInBasket(
    name: String,
    price: Int,
    imgPath: String
) {
    Surface(
        elevation = MaterialTheme.spacing.xs,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.m,
                vertical = MaterialTheme.spacing.s
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(MaterialTheme.spacing.s)
                .height(125.dp)
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit,
                painter = rememberAsyncImagePainter(imgPath),
                contentDescription = "Bike image" // Todo: strings
            )

            Column() {
                Text(text = name, style = Info)

                Spacer(Modifier.width(MaterialTheme.spacing.xl))

                Text(text = "$price,-", style = B3, color = Primary)
            }
        }
    }
}

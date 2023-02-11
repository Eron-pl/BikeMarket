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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.psablik.bikemarket.presentation.ui.theme.B5
import com.psablik.bikemarket.presentation.ui.theme.Info
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.statusPreparedForSending
import com.psablik.bikemarket.ui.theme.statusPreparing
import com.psablik.bikemarket.ui.theme.statusSent

@Composable
fun OrderUser(
    imgPath: String,
    name: String,
    status: String
) {

    val statusColor = when (status) {
        "Preparing" -> statusPreparing
        "Prepared for sending" -> statusPreparedForSending
        "Sent" -> statusSent
        "Completed" -> statusSent
        else -> Color.Red
    }

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

            Column {
                Text(text = name, style = Info)

                Spacer(Modifier.width(MaterialTheme.spacing.xl))

                Row {
                    Text(text = "Status: ", style = B5)
                    Text(text = status, color = statusColor, style = B5)
                }
            }
        }
    }
}
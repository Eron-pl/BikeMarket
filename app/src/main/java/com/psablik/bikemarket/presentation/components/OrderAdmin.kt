package com.psablik.bikemarket.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.psablik.bikemarket.presentation.ui.theme.B4
import com.psablik.bikemarket.presentation.ui.theme.B5
import com.psablik.bikemarket.presentation.ui.theme.B6
import com.psablik.bikemarket.presentation.ui.theme.H4
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.statusPreparedForSending
import com.psablik.bikemarket.ui.theme.statusPreparing
import com.psablik.bikemarket.ui.theme.statusSent

@Composable
fun OrderAdmin(
    status: String,
    imgPath: String,
    name: String,
    client: String,
    orderId: String,
    changeStatus: (String, String) -> Unit
) {
    fun getStatusColor(status: String): Color = when (status) {
        "Preparing" -> statusPreparing
        "Prepared for sending" -> statusPreparedForSending
        "Sent" -> statusSent
        "Completed" -> statusSent
        else -> Color.Red
    }

    val statusOptions = listOf(
        "Preparing", "Prepared for sending", "Sent", "Completed"
    )

    Surface(
        elevation = MaterialTheme.spacing.xs,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.m,
                vertical = MaterialTheme.spacing.s
            )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                    Text(text = name, style = B4)

                    Spacer(Modifier.height(MaterialTheme.spacing.s))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Status: ", style = B4)
                        Text(text = status, color = getStatusColor(status), style = B6)
                    }

                    Spacer(Modifier.height(MaterialTheme.spacing.s))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Client: ", style = B4)
                        Text(text = client, style = B6)
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))

            Column(Modifier.padding(MaterialTheme.spacing.xs)) {
                Text(
                    text = "Change status",
                    style = H4,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.xxs)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    statusOptions.forEach { status ->
                        TextButton(onClick = { changeStatus(orderId, status) }) {
                            Text(text = status, color = getStatusColor(status), style = B5)
                        }
                    }
                }
            }
        }
    }
}
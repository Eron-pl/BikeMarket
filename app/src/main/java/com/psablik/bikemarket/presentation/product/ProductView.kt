package com.psablik.bikemarket.presentation.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.components.ProgressIndicator
import com.psablik.bikemarket.presentation.ui.theme.B4
import com.psablik.bikemarket.presentation.ui.theme.H3
import com.psablik.bikemarket.presentation.ui.theme.H4
import com.psablik.bikemarket.presentation.ui.theme.Info
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Primary
import com.psablik.bikemarket.utils.LaunchOnce

@Composable
fun ProductView(
    productId: String,
    viewModel: ProductViewModel = hiltViewModel()
) {
    LaunchedEffect(LaunchOnce) {
        viewModel.init(productId)
    }

    val state = viewModel.state

    Box {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (state is ProductViewState.Loading) {
                ProgressIndicator()
            }

            if (state is ProductViewState.Loaded) {
                with(state) {
                    ImageSection(url = bikeImgPath)

                    Spacer(Modifier.height(MaterialTheme.spacing.s))

                    TitleAndPriceSection(bikePrice = bikePrice, bikeName = bikeName)

                    Spacer(Modifier.height(MaterialTheme.spacing.s))

                    DescriptionSection(bikeName = bikeName, bikeDescription = bikeDescription)
                }
            }
        }
        Spacer(Modifier.height(MaterialTheme.spacing.s))

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            BuyButtonSection(onClick = { })
        }
    }


}

@Composable
fun TitleAndPriceSection(bikePrice: Int, bikeName: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.s)
    ) {
        Text(text = bikeName, style = H4, modifier = Modifier.weight(8f, fill = true))
        Text(text = "$bikePrice,-", style = H3, color = Primary)
    }
}

@Composable
fun ImageSection(url: String) {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.l)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "Bike image" // Todo: strings
        )
    }
}

@Composable
fun DescriptionSection(
    bikeName: String,
    bikeDescription: String
) {
    Column(Modifier.padding(MaterialTheme.spacing.s)) {
        Text(text = bikeDescription, style = Info)
    }
}

@Composable
fun BuyButtonSection(onClick: () -> Unit) {
    BaseButton(
        backgroundColor = Primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.m, vertical = MaterialTheme.spacing.s),
        onClick = onClick
    ) {
        Text(text = "Buy", style = B4, color = Color.White)
    }
}


package com.psablik.bikemarket.presentation.payment

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
import androidx.navigation.NavController
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.components.BikeInBasket
import com.psablik.bikemarket.presentation.ui.theme.B4
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Primary
import com.psablik.bikemarket.utils.LaunchOnce

@Composable
fun PaymentView(
    navController: NavController,
    productId: String,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(LaunchOnce) {
        viewModel.init(productId)
    }

    Box(Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            Spacer(Modifier.height(MaterialTheme.spacing.m))

            if (state is PaymentViewState.Loaded) {
                with(state) {
                    BikeInBasket(name = bikeName, price = bikePrice, imgPath = bikeImgPath)
                }
            }
        }

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            BaseButton(
                backgroundColor = Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.m,
                        vertical = MaterialTheme.spacing.s
                    )
            ) {
                Text(text = "Pay", color = Color.White, style = B4)
            }
        }
    }
}
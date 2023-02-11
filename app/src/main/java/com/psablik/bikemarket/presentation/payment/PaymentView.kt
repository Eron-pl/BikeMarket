package com.psablik.bikemarket.presentation.payment

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.components.BikeInBasket
import com.psablik.bikemarket.presentation.components.ProgressIndicator
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
    val context = LocalContext.current

    LaunchedEffect(LaunchOnce) {
        viewModel.init(productId)
    }

    LaunchedEffect(LaunchOnce) {
        viewModel.event.collect { event ->
            when (event) {
                is PaymentEvent.PaidAndPlacedOrder -> {
                    Toast.makeText(context, "Order placed successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.Home.route)
                }
                is PaymentEvent.PaymentFailed -> {
                    Toast.makeText(context, "Order failed", Toast.LENGTH_LONG).show()
                }
            }
        }
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

        if (state is PaymentViewState.Loading) {
            ProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            BaseButton(
                enabled = if (state is PaymentViewState.Loaded) state.isPayButtonEnabled else false,
                onClick = { viewModel.payAndPlaceOrder() },
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
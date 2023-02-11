package com.psablik.bikemarket.presentation.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.psablik.bikemarket.domain.model.Order
import com.psablik.bikemarket.presentation.components.OrderUser
import com.psablik.bikemarket.presentation.components.ProgressIndicator
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.Info
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant

@Composable
fun OrdersView(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(MaterialTheme.spacing.m))

        TitleSection()

        if (state is OrdersViewState.Loading) {
            ProgressIndicator()
        }

        if (state is OrdersViewState.Loaded) {
            if (state.orders.isEmpty()) {
                Text(
                    text = "You don't have any orders", // Todo: Strings
                    style = Info,
                    color = Color.Gray,
                )
            } else {
                OrdersSection(orders = state.orders)
            }
        }
    }

}

@Composable
fun TitleSection() {
    Text(
        text = "Your orders", // Todo: Strings
        style = H2,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )
}

@Composable
fun OrdersSection(orders: List<Order>) {
    LazyColumn(
        modifier = Modifier.padding(top = MaterialTheme.spacing.m)
    ) {
        items(orders) { order ->
            with(order) {
                OrderUser(imgPath = bike.imgPath, name = bike.name, status = status)
            }
        }
    }
}

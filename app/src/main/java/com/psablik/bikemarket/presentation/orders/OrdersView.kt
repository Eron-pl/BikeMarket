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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.psablik.bikemarket.domain.model.Order
import com.psablik.bikemarket.presentation.components.ProgressIndicator
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant

@Composable
fun OrdersView(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val state = viewModel.state


    Column(Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(MaterialTheme.spacing.m))

        TitleSection()

        Spacer(Modifier.height(MaterialTheme.spacing.m))

        if (state is OrdersViewState.Loading) {
            ProgressIndicator()
        }

        if (state is OrdersViewState.Loaded) {
            OrdersSection(orders = state.orders)
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

    Spacer(Modifier.height(MaterialTheme.spacing.xs))
}

@Composable
fun OrdersSection(orders: List<Order>) {
    LazyColumn {
        items(orders) { order ->
            if (orders.isEmpty()) {
                Text(text = "You don't have any orders")
            } else {
                Text(text = order.bike.name)
            }
        }
    }
}
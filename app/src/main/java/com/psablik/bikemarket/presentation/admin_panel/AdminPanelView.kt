package com.psablik.bikemarket.presentation.admin_panel

import android.widget.Toast
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.psablik.bikemarket.domain.model.Order
import com.psablik.bikemarket.presentation.components.OrderAdmin
import com.psablik.bikemarket.presentation.components.ProgressIndicator
import com.psablik.bikemarket.presentation.ui.theme.B5
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant
import com.psablik.bikemarket.utils.LaunchOnce

@Composable
fun AdminPanelView(
    viewModel: AdminPanelViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(LaunchOnce) {
        viewModel.event.collect { event ->
            when(event) {
                is AdminPanelViewEvent.OrderStatusChangedSuccessfully -> {
                    Toast.makeText(context, "Order status changed successfully", Toast.LENGTH_SHORT).show()
                }
                is AdminPanelViewEvent.OrderStatusChangeFailed -> {
                    Toast.makeText(context, "Order status change failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            Spacer(Modifier.height(MaterialTheme.spacing.s))
            TitleSection()

            if (state is AdminPanelViewState.Loaded) {
                AdminOrdersSection(
                    orders = state.orders,
                    changeStatus = { orderId, status ->
                        viewModel.changeOrderStatus(orderId = orderId, status = status)
                    })
            }
        }

        if (state is AdminPanelViewState.Loading) {
            ProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Clients' orders", // Todo: Strings
        style = H2,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )

    Spacer(Modifier.height(MaterialTheme.spacing.xs))

    Text(
        text = "Browse orders and manage their status", // Todo: Strings
        style = B5,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )
}

@Composable
fun AdminOrdersSection(orders: List<Order>, changeStatus: (String, String) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(top = MaterialTheme.spacing.m)
    ) {
        items(orders) { order ->
            with(order) {
                user.name?.let { name ->
                    OrderAdmin(
                        changeStatus = changeStatus,
                        imgPath = bike.imgPath,
                        name = bike.name,
                        client = name,
                        status = status,
                        orderId = order.id
                    )
                }
            }
        }
    }
}
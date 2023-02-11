package com.psablik.bikemarket.presentation.orders

import com.psablik.bikemarket.domain.model.Order

sealed class OrdersViewState {
    object Idle : OrdersViewState()
    object Loading : OrdersViewState()
    data class Loaded(val orders: List<Order>) : OrdersViewState()
    data class Error(val message: String) : OrdersViewState()
}

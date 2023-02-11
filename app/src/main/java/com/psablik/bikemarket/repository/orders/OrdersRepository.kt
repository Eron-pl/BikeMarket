package com.psablik.bikemarket.repository.orders

import com.psablik.bikemarket.domain.model.Order

interface OrdersRepository {
    suspend fun addNewOrder(
        userId: String,
        bikeId: String
    ): Boolean

    suspend fun getUserOrders(userId: String): List<Order>
}
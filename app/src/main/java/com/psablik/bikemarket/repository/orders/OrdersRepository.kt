package com.psablik.bikemarket.repository.orders

import com.psablik.bikemarket.domain.model.Order
import com.psablik.bikemarket.domain.model.OrderStatus

interface OrdersRepository {
    suspend fun addNewOrder(
        userId: String,
        bikeId: String
    ): Boolean
    suspend fun changeOrderStatus(orderId: String, status: OrderStatus): Boolean
    suspend fun getOrders(): List<Order>
    suspend fun getUserOrders(userId: String): List<Order>
}
package com.psablik.bikemarket.repository.orders

interface OrdersRepository {
    suspend fun addNewOrder(
        userId: String,
        bikeId: String
    )
}
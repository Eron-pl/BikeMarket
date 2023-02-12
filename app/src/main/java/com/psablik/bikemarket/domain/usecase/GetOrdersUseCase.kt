package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.domain.model.Order
import com.psablik.bikemarket.repository.orders.OrdersRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend operator fun invoke(): List<Order> = repository.getOrders()
}
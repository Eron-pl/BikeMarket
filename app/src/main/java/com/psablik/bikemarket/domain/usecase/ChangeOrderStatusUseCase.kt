package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.domain.model.OrderStatus
import com.psablik.bikemarket.repository.orders.OrdersRepository
import javax.inject.Inject

class ChangeOrderStatusUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend operator fun invoke(orderId: String, status: OrderStatus) =
        repository.changeOrderStatus(orderId = orderId, status = status)
}

package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.orders.OrdersRepository
import javax.inject.Inject

class BuyProductUseCase @Inject constructor(
    private val repository: OrdersRepository,
    private val getUserId: GetUserIdUseCase
) {
    suspend operator fun invoke(bikeId: String): Boolean =
        repository.addNewOrder(userId = getUserId(), bikeId = bikeId)
}
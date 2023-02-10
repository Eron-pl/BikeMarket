package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.orders.OrdersRepository
import javax.inject.Inject

class AddNewOrderUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
//    suspend operator fun invoke() {
//        repository.addNewOrder()
//    }
}
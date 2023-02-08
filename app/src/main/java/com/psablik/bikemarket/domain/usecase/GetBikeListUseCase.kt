package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.domain.model.Bike
import com.psablik.bikemarket.repository.bikes.BikesRepository
import javax.inject.Inject

class GetBikeListUseCase @Inject constructor(
    private val  repository: BikesRepository
) {
    suspend operator fun invoke(): List<Bike> = repository.getBikes()
}

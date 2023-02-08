package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.bikes.BikesRepository
import javax.inject.Inject

class GetBikeUseCase @Inject constructor(
    private val repository: BikesRepository
) {
    suspend operator fun invoke(id: String) = repository.getBike(id)
}

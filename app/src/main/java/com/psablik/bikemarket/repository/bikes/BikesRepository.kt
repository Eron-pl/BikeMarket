package com.psablik.bikemarket.repository.bikes

import com.psablik.bikemarket.domain.model.Bike

interface BikesRepository {
    suspend fun getBikes(): List<Bike>
}

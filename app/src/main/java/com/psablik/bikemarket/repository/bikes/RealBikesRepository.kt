package com.psablik.bikemarket.repository.bikes

import com.psablik.bikemarket.domain.model.Bike
import com.psablik.bikemarket.infrastructure.remote.FirestoreDataSource
import com.psablik.bikemarket.mapper.domain.BikeMapper
import javax.inject.Inject

class RealBikesRepository @Inject constructor(
    private val firestoreDataSource: FirestoreDataSource,
    private val bikeMapper: BikeMapper
) : BikesRepository {
    override suspend fun getBikes(): List<Bike> =
        firestoreDataSource.getBikes().map { bikeMapper(it) }
}

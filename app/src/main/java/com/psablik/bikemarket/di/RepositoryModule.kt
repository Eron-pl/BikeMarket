package com.psablik.bikemarket.di

import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.FirestoreDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.mapper.domain.BikeMapper
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
import com.psablik.bikemarket.repository.authentication.AuthenticationRepository
import com.psablik.bikemarket.repository.authentication.RealAuthenticationRepository
import com.psablik.bikemarket.repository.bikes.BikesRepository
import com.psablik.bikemarket.repository.bikes.RealBikesRepository
import com.psablik.bikemarket.repository.orders.OrdersRepository
import com.psablik.bikemarket.repository.orders.RealOrdersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun localAuthRepository(
        localDataSource: LocalAuthenticationDataSource,
        remoteDataSource: RemoteAuthenticationDataSource,
        userMapper: UserMapper,
        loggedStatusMapper: LoggedStatusMapper
    ): AuthenticationRepository = RealAuthenticationRepository(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        userMapper = userMapper,
        loggedStatusMapper = loggedStatusMapper
    )

    @Provides
    @Singleton
    fun bikesRepository(
        firestoreDataSource: FirestoreDataSource,
        bikeMapper: BikeMapper
    ): BikesRepository = RealBikesRepository(
        firestoreDataSource = firestoreDataSource,
        bikeMapper = bikeMapper
    )

    @Provides
    @Singleton
    fun ordersRepository(
        firestoreDataSource: FirestoreDataSource,
        bikeMapper: BikeMapper,
        userMapper: UserMapper
    ): OrdersRepository = RealOrdersRepository(
        firestoreDataSource = firestoreDataSource,
        userMapper = userMapper,
        bikeMapper = bikeMapper
    )
}
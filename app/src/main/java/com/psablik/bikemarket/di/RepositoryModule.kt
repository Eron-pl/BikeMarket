package com.psablik.bikemarket.di

import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.repository.AuthenticationRepository
import com.psablik.bikemarket.repository.RealAuthenticationRepository
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
        loggedStatusMapper: LoggedStatusMapper
    ): AuthenticationRepository = RealAuthenticationRepository(
        localDataSource = localDataSource,
        loggedStatusMapper = loggedStatusMapper
    )

}
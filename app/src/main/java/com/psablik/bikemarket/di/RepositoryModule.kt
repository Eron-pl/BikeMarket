package com.psablik.bikemarket.di

import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.repository.AuthenticationRepository
import com.psablik.bikemarket.repository.RealAuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun localAuthRepository(
        localDataSource: LocalAuthenticationDataSource
    ): AuthenticationRepository = RealAuthenticationRepository(
        localDataSource
    )

}
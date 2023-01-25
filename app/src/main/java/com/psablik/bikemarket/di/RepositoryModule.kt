package com.psablik.bikemarket.di

import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
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
        remoteDataSource: RemoteAuthenticationDataSource,
        userMapper: UserMapper,
        loggedStatusMapper: LoggedStatusMapper
    ): AuthenticationRepository = RealAuthenticationRepository(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        userMapper = userMapper,
        loggedStatusMapper = loggedStatusMapper
    )

}
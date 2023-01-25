package com.psablik.bikemarket.di

import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteUserDataSource
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
import com.psablik.bikemarket.mapper.domain.UserTypeMapper
import com.psablik.bikemarket.repository.authentication.AuthenticationRepository
import com.psablik.bikemarket.repository.authentication.RealAuthenticationRepository
import com.psablik.bikemarket.repository.user.RealUserRepository
import com.psablik.bikemarket.repository.user.UserRepository
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
    fun authenticationRepository(
        localDataSource: LocalAuthenticationDataSource,
        remoteDataSource: RemoteAuthenticationDataSource,
        loggedStatusMapper: LoggedStatusMapper
    ): AuthenticationRepository = RealAuthenticationRepository(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        loggedStatusMapper = loggedStatusMapper
    )

    @Provides
    @Singleton
    fun userRepository(
        userMapper: UserMapper,
        userTypeMapper: UserTypeMapper,
        remoteDataSource: RemoteUserDataSource
    ): UserRepository = RealUserRepository(
        userMapper = userMapper,
        userTypeMapper = userTypeMapper,
        remoteDataSource = remoteDataSource
    )

}
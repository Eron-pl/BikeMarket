package com.psablik.bikemarket.repository.user

import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.domain.model.UserType
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteUserDataSource
import com.psablik.bikemarket.mapper.domain.UserMapper
import com.psablik.bikemarket.mapper.domain.UserTypeMapper
import javax.inject.Inject

class RealUserRepository @Inject constructor(
    private val  userMapper: UserMapper,
    private val userTypeMapper: UserTypeMapper,
    private val remoteDataSource: RemoteUserDataSource,
) : UserRepository {

    override fun getCurrentUser(): User? =
        remoteDataSource.getCurrentUser()?.let { firebaseUser ->
            userMapper(firebaseUser)
        }

    override suspend fun isUserRegistered(): Boolean = remoteDataSource.isUserRegistered()

    override suspend fun registerUser(): Result<Unit> = remoteDataSource.registerUser()

    override suspend fun getUserType(): UserType = userTypeMapper(remoteDataSource.getUserType())

}

package com.psablik.bikemarket.repository.authentication

import com.google.firebase.auth.AuthCredential
import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RealAuthenticationRepository @Inject constructor(
    private val localDataSource: LocalAuthenticationDataSource,
    private val remoteDataSource: RemoteAuthenticationDataSource,
    private val loggedStatusMapper: LoggedStatusMapper,
    private val userMapper: UserMapper
) : AuthenticationRepository {

    override suspend fun signInWithCredential(credential: AuthCredential): Result<Unit> =
        remoteDataSource.signInWithCredential(credential)

    override suspend fun logOut(): Result<Unit> = remoteDataSource.logOut()

    override fun getCurrentUser(): User? =
        remoteDataSource.getCurrentUser()?.let { firebaseUser ->
            userMapper(firebaseUser)
        }

    override suspend fun getLoggedInStatus(): Boolean =
        localDataSource.getLoggedStatus().first() ?: NOT_LOGGED_IN

    override suspend fun clearLoggedInStatus() {
        localDataSource.clearLoggedStatus()
    }

    override suspend fun setLoggedInStatus(status: LoggedStatus) {
        localDataSource.setLoggedStatus(loggedStatusMapper(status))
    }

    companion object {
        private const val NOT_LOGGED_IN = false
    }
}

package com.psablik.bikemarket.repository

import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RealAuthenticationRepository @Inject constructor(
    private val localDataSource: LocalAuthenticationDataSource
) : AuthenticationRepository {

    companion object {
        private const val NOT_LOGGED_IN = false
    }

    override suspend fun getLoggedInStatus() =
        localDataSource.getLoggedStatus().first() ?: NOT_LOGGED_IN

    override suspend fun clearLoggedInStatus() {
        localDataSource.clearLoggedStatus()
    }

    override suspend fun setLoggedInStatus(status: Boolean) {
        localDataSource.setLoggedStatus(status)
    }

}

package com.psablik.bikemarket.repository

import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RealAuthenticationRepository @Inject constructor(
    private val localDataSource: LocalAuthenticationDataSource,
    private val loggedStatusMapper: LoggedStatusMapper
) : AuthenticationRepository {

    companion object {
        private const val NOT_LOGGED_IN = false
    }

    override suspend fun getLoggedInStatus() =
        localDataSource.getLoggedStatus().first() ?: NOT_LOGGED_IN

    override suspend fun clearLoggedInStatus() {
        localDataSource.clearLoggedStatus()
    }

    override suspend fun setLoggedInStatus(status: LoggedStatus) {
        localDataSource.setLoggedStatus(loggedStatusMapper(status))
    }

}

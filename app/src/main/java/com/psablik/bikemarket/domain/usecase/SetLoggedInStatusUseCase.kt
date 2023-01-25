package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.repository.AuthenticationRepository
import javax.inject.Inject

class SetLoggedInStatusUseCase @Inject constructor(
    private val  repository: AuthenticationRepository
)  {
    suspend operator fun invoke(status: LoggedStatus) =
        if(status == LoggedStatus.LOGGED_IN) {
            repository.setLoggedInStatus(status)
        } else {
            repository.clearLoggedInStatus()
        }
    }

package com.psablik.bikemarket.repository.authentication

import com.google.firebase.auth.AuthCredential
import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.FirestoreDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
import com.psablik.bikemarket.mapper.domain.UserType
import com.psablik.bikemarket.mapper.domain.UserTypeMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.first

class RealAuthenticationRepository @Inject constructor(
    private val localDataSource: LocalAuthenticationDataSource,
    private val remoteDataSource: RemoteAuthenticationDataSource,
    private val firestoreDataSource: FirestoreDataSource,
    private val loggedStatusMapper: LoggedStatusMapper,
    private val userTypeMapper: UserTypeMapper,
    private val userMapper: UserMapper
) : AuthenticationRepository {

    override suspend fun signInWithCredential(credential: AuthCredential): Result<Unit> =
        remoteDataSource.signInWithCredential(credential)

    override suspend fun logOut(): Result<Unit> = remoteDataSource.logOut()

    override fun getCurrentUser(): User? =
        remoteDataSource.getCurrentUser()?.let { firebaseUser ->
            userMapper(firebaseUser)
        }

    override suspend fun getLoggedInStatus(): LoggedStatus =
        loggedStatusMapper(localDataSource.getLoggedStatus().first())

    override suspend fun clearLoggedInStatus() {
        localDataSource.clearLoggedStatus()
    }

    override suspend fun setLoggedInStatus(status: LoggedStatus) {
        localDataSource.setLoggedStatus(loggedStatusMapper(status))
    }

    override suspend fun getCurrentUserType(userId: String): UserType =
        userTypeMapper(firestoreDataSource.getCurrentUserType(userId))

    override suspend fun checkIfUserExists(): Boolean =
       remoteDataSource.checkIfUserExists()

    override suspend fun addNewUser() {
        remoteDataSource.addUserToFirestore()
    }
}

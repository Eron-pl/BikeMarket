package com.psablik.bikemarket.infrastructure.remote

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class RemoteAuthenticationDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) {
    suspend fun signInWithCredential(credential: AuthCredential): Result<Unit> {
        return try {
            firebaseAuth.signInWithCredential(credential).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Timber.e("signInWithCredential: $e")
            Result.failure(e)
        }
    }

    suspend fun logOut(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            googleSignInClient.signOut().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Timber.e("signOut: $e")
            Result.failure(e)
        }
    }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}
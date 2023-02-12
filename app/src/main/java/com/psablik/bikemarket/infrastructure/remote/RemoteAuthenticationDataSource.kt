package com.psablik.bikemarket.infrastructure.remote

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.mapper.domain.UserMapper
import java.security.InvalidParameterException
import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class RemoteAuthenticationDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient,
    private val firestore: FirebaseFirestore,
    private val userMapper: UserMapper
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

    suspend fun checkIfUserExists(): Boolean {
        val xd =  getCurrentUser()?.email?.let {
            firestore.collection(COLLECTION_USER_KEY)
                .document(it)
                .get()
                .await()
                .exists()

        } ?: false
        return  xd
    }

    suspend fun addUserToFirestore(): Result<Unit> =
        try {
            firebaseAuth.currentUser?.let {
                val user = userMapper(it)
                firestore.collection(COLLECTION_USER_KEY)
                    .document(user.email ?: user.uid)
                    .set(user)
                    .await()
            } ?: throw InvalidParameterException()
            Result.success(Unit)
        } catch (e: Exception) {
            Timber.tag("Firebase").e("Could not add current user to the database, $e")
            Result.failure(e)
        }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    companion object {
        private const val COLLECTION_USER_KEY = "Users"
    }
}
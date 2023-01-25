package com.psablik.bikemarket.infrastructure.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.domain.error.NoLoggedInUserException
import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.infrastructure.model.UserResponse
import com.psablik.bikemarket.mapper.domain.UserMapper
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userMapper: UserMapper,
    private val firestore: FirebaseFirestore
) {

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    suspend fun registerUser(): Result<Unit> =
        try {
            firebaseAuth.currentUser?.let {
                val user = userMapper(it)
                firestore.collection(COLLECTION_KEY_USERS)
                    .document(user.email ?: user.uid)
                    .set(user)
                    .await()
            } ?: throw NoLoggedInUserException()
            Result.success(Unit)
        } catch (e: Exception) {
            Timber.tag("Firebase").e("Could not add current user to the database, $e")
            Result.failure(e)
        }

    suspend fun isUserRegistered(): Boolean =
        firebaseAuth.currentUser?.let { firebaseUser ->
            val userDocumentSnapshot = firestore.collection(COLLECTION_KEY_USERS)
                .document(firebaseUser.email ?: "")
                .get()
                .await()
            userDocumentSnapshot.data != null
        } ?: throw NoLoggedInUserException()

    suspend fun getUserType(): String? {
        return firebaseAuth.currentUser?.let { firebaseUser ->
            val user = userMapper(firebaseUser)
            firestore.collection(COLLECTION_KEY_USERS)
                .document(user.email ?: user.uid)
                .get()
                .await()
                .toObject(UserResponse::class.java)?.type
        }
    }

    companion object {
        private const val COLLECTION_KEY_USERS = "Users"
    }
}
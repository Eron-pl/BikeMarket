package com.psablik.bikemarket.infrastructure.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.domain.error.WrongBikeIdException
import com.psablik.bikemarket.infrastructure.model.BikeResponse
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class FirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    suspend fun getBikes(): List<BikeResponse> =
        firestore.collection(COLLECTION_BIKES_KEY)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(BikeResponse::class.java) }

    suspend fun getBike(id: String): BikeResponse =
        firestore.collection(COLLECTION_BIKES_KEY)
            .document(id)
            .get()
            .await()
            .toObject(BikeResponse::class.java)
            ?: throw WrongBikeIdException



    companion object {
        private const val COLLECTION_BIKES_KEY = "Bikes"
    }

}
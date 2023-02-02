package com.psablik.bikemarket.infrastructure.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.infrastructure.model.BikeResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {

    suspend fun getBikes(): List<BikeResponse> =

//        firestore.collection(COLLECTION_BIKES_KEY)
//            .get()
//            .addOnSuccessListener { documents ->
//                bikes.addAll(documents.toObjects(Bike::class.java))
//            }
//        return bikes
        firestore.collection(COLLECTION_BIKES_KEY)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(BikeResponse::class.java) }



    companion object {
        private const val COLLECTION_BIKES_KEY = "Bikes"
    }

}
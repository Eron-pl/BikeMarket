package com.psablik.bikemarket.infrastructure.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.domain.error.LoadingDataFromFirestoreException
import com.psablik.bikemarket.domain.error.WrongBikeIdException
import com.psablik.bikemarket.infrastructure.model.BikeResponse
import com.psablik.bikemarket.infrastructure.model.OrderResponse
import com.psablik.bikemarket.infrastructure.model.UserResponse
import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class FirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    suspend fun getBikes(): List<BikeResponse> =
        try {
            firestore.collection(COLLECTION_BIKES_KEY)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(BikeResponse::class.java) }
        } catch (e: Exception) {
            throw LoadingDataFromFirestoreException("Error while loading bike list from firestore")
        }

    suspend fun getBike(id: String): BikeResponse =
        try {
            firestore.collection(COLLECTION_BIKES_KEY)
                .document(id)
                .get()
                .await()
                .toObject(BikeResponse::class.java)
                ?: throw WrongBikeIdException
        } catch (e: Exception) {
            throw LoadingDataFromFirestoreException("Error while loading bike from firestore")
        }

    suspend fun addNewOrder(
        orderId: String,
        orderStatus: String,
        bikeId: String,
        userId: String,
    ): Result<Unit> {
        return try {
            firestore.collection(COLLECTION_ORDERS_KEY)
                .document(orderId)
                .set(
                    mapOf(
                        FIELD_BIKE_ID_KEY to bikeId,
                        FIELD_ORDER_ID_KEY to orderId,
                        FIELD_ORDER_STATUS_KEY to orderStatus,
                        FIELD_USER_ID_KEY to userId
                    )
                )
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun assignOrderToUser(
        orderId: String,
        userId: String
    ): Result<Unit> {
        return try {
            firestore.collection(COLLECTION_USERS_KEY)
                .document(userId)
                .update(FIELD_USER_ORDERS_KEY, FieldValue.arrayUnion(orderId))
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkIfOrderIdExists(id: String): Boolean =
        firestore.collection(COLLECTION_ORDERS_KEY)
            .document(id)
            .get()
            .await()
            .exists()

    @Suppress("UNCHECKED_CAST")
    suspend fun getUserOrdersIds(userId: String): List<String> =
        try {
            firestore.collection(COLLECTION_USERS_KEY)
                .document(userId)
                .get()
                .await()
                .get(FIELD_USER_ORDERS_KEY) as List<String>
        } catch (e: Exception) {
            Timber.e("Fire: ${e.message}")
            emptyList()
        }


    suspend fun getBikeByOrderId(orderId: String): BikeResponse {
        val bikeId = firestore.collection(COLLECTION_ORDERS_KEY)
            .document(orderId)
            .get()
            .await()
            .getString(FIELD_BIKE_ID_KEY)

        return bikeId?.let { getBike(it) } ?: BikeResponse()
    }

    suspend fun getUserByOrderId(orderId: String): UserResponse {
        val userId = firestore.collection(COLLECTION_ORDERS_KEY)
            .document(orderId)
            .get()
            .await()
            .getString(FIELD_USER_ID_KEY)

        val u = userId?.let { getUser(it) } ?: UserResponse()

        return u
    }

    suspend fun getUser(userId: String): UserResponse? =
        firestore.collection(COLLECTION_USERS_KEY)
            .document(userId)
            .get()
            .await()
            .toObject(UserResponse::class.java)

    suspend fun getOrderStatus(orderId: String): String =
        firestore.collection(COLLECTION_ORDERS_KEY)
            .document(orderId)
            .get()
            .await()
            .getString(FIELD_ORDER_STATUS_KEY) ?: "ERROR"

    suspend fun getCurrentUserType(userId: String): String =
        firestore.collection(COLLECTION_USERS_KEY)
            .document(userId)
            .get()
            .await()
            .getString(FIELD_USER_TYPE_KEY) ?: ""

    suspend fun getOrders(): List<OrderResponse> =
        try {
            firestore.collection(COLLECTION_ORDERS_KEY)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(OrderResponse::class.java) }
        } catch (e: Exception) {
            throw LoadingDataFromFirestoreException("Error while loading order list from firestore")
        }

    suspend fun changeOrderStatus(orderId: String, status: String): Result<Unit> =
        try {
            firestore.collection(COLLECTION_ORDERS_KEY)
                .document(orderId)
                .update(FIELD_ORDER_STATUS_KEY, status)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Timber.e(e.message)
            Result.failure(e)
        }


    companion object {
        private const val COLLECTION_BIKES_KEY = "Bikes"
        private const val COLLECTION_ORDERS_KEY = "Orders"
        private const val COLLECTION_USERS_KEY = "Users"
        private const val FIELD_BIKE_ID_KEY = "bikeId"
        private const val FIELD_ORDER_STATUS_KEY = "status"
        private const val FIELD_USER_ID_KEY = "userId"
        private const val FIELD_USER_ORDERS_KEY = "orders"
        private const val FIELD_USER_TYPE_KEY = "type"
        private const val FIELD_ORDER_ID_KEY = "orderId"
    }
}
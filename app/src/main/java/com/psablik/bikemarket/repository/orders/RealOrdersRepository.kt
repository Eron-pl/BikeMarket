package com.psablik.bikemarket.repository.orders

import com.psablik.bikemarket.domain.model.Order
import com.psablik.bikemarket.domain.model.OrderStatus
import com.psablik.bikemarket.infrastructure.remote.FirestoreDataSource
import com.psablik.bikemarket.mapper.domain.BikeMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
import com.psablik.bikemarket.utils.generateRandomId
import javax.inject.Inject

class RealOrdersRepository @Inject constructor(
    private val firestoreDataSource: FirestoreDataSource,
    private val bikeMapper: BikeMapper,
    private val userMapper: UserMapper
) : OrdersRepository {
    override suspend fun addNewOrder(userId: String, bikeId: String): Boolean {
        var orderId = generateRandomId()

        with(firestoreDataSource) {
            while (checkIfOrderIdExists(orderId)) {
                orderId = generateRandomId()
            }
            val orderAdded = addNewOrder(
                userId = userId,
                bikeId = bikeId,
                orderId = orderId,
                orderStatus = OrderStatus.PREPARING.name
            )
            val orderAssignedToUser = assignOrderToUser(orderId = orderId, userId = userId)

            return orderAdded.isSuccess && orderAssignedToUser.isSuccess
        }
    }

    override suspend fun getUserOrders(userId: String): List<Order> {
        val ordersIds = firestoreDataSource.getUserOrdersIds(userId)
        return ordersIds.map { order ->
            Order(
                id = order,
                bike = bikeMapper(firestoreDataSource.getBikeByOrderId(order)),
                status = firestoreDataSource.getOrderStatus(order),
                user = userMapper(firestoreDataSource.getUserByOrderId(order))
            )
        }
    }

    override suspend fun getOrders(): List<Order> {
        val orders = firestoreDataSource.getOrders()
        return orders.map { order ->
            Order(
                id = order.orderId!!,
                bike = bikeMapper(firestoreDataSource.getBikeByOrderId(order.orderId)),
                status = firestoreDataSource.getOrderStatus(order.orderId),
                user = userMapper(firestoreDataSource.getUserByOrderId(order.orderId))
            )
        }
    }

    override suspend fun changeOrderStatus(orderId: String, status: OrderStatus): Boolean =
        firestoreDataSource.changeOrderStatus(orderId = orderId, status = status.name).isSuccess
}

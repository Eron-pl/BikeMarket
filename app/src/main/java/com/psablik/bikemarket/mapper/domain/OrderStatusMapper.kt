package com.psablik.bikemarket.mapper.domain

import com.psablik.bikemarket.domain.model.OrderStatus
import java.security.InvalidParameterException

class OrderStatusMapper {
    operator fun invoke(status: String): OrderStatus =
        when (status) {
            "Preparing" -> OrderStatus.PREPARING // Todo: to strings
            "Prepared for sending" -> OrderStatus.PREPARED
            "Sent" -> OrderStatus.SENT
            "Completed" -> OrderStatus.COMPLETED
            else -> throw InvalidParameterException()
        }
}

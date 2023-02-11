package com.psablik.bikemarket.mapper.view

import com.psablik.bikemarket.domain.model.OrderStatus

class StatusOnViewMapper {
    operator fun invoke(status: String): String =
        when (status) {
            OrderStatus.PREPARING.name -> "Preparing" // Todo: to strings
            OrderStatus.PREPARED.name -> "Prepared for sending"
            OrderStatus.SENT.name -> "Sent"
            OrderStatus.COMPLETED.name -> "Completed"
            else -> "Unknown status"
        }
}
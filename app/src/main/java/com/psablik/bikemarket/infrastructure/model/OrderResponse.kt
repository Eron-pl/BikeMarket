package com.psablik.bikemarket.infrastructure.model

import com.google.errorprone.annotations.Keep

@Keep
data class OrderResponse(
    val orderId: String? = null,
    val bikeId: String? = null,
    val status: String? = null,
    val userId: String? = null
)
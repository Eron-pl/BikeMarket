package com.psablik.bikemarket.infrastructure.model

import com.google.errorprone.annotations.Keep
import com.psablik.bikemarket.domain.model.Bike

@Keep
data class BasketResponse(
    val bikes: List<Bike>? = null,
    val totalPrice: Int? = null
)

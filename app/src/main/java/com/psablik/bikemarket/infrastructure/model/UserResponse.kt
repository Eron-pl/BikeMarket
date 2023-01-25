package com.psablik.bikemarket.infrastructure.model

import com.google.errorprone.annotations.Keep

@Keep
data class UserResponse(
    val uid: String? = null,
    val email: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val type: String? = null
)

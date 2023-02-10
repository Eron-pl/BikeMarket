package com.psablik.bikemarket.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val auth: FirebaseAuth
) {
    operator fun invoke(): String = auth.currentUser?.email ?: ""
}

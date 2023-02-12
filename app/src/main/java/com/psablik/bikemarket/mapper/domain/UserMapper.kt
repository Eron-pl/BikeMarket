package com.psablik.bikemarket.mapper.domain

import com.google.firebase.auth.FirebaseUser
import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.infrastructure.model.UserResponse

class UserMapper {
    operator fun invoke(firebaseUser: FirebaseUser): User = with(firebaseUser) {
        User(
            uid = uid,
            name = displayName,
            email = email,
            photoUrl = photoUrl.toString()
        )
    }

    operator fun invoke(user: UserResponse): User = with(user) {
        User(
            uid = uid ?: "",
            email = email ?: "",
            name = name ?: "",
            photoUrl = photoUrl ?: "",
            type = type ?: ""
        )
    }
}

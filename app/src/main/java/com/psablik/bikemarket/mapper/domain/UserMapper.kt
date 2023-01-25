package com.psablik.bikemarket.mapper.domain

import com.google.firebase.auth.FirebaseUser
import com.psablik.bikemarket.domain.model.User

class UserMapper {
    operator fun invoke(firebaseUser: FirebaseUser): User = with(firebaseUser) {
        User(
            uid = uid,
            name = displayName,
            email = email,
            photoUrl = photoUrl.toString(),
        )
    }
}

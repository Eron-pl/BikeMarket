package com.psablik.bikemarket.domain.model

import com.google.errorprone.annotations.Keep

@Keep
data class User(
    val uid: String,
    val email: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val type: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is User -> (
                    uid == other.uid &&
                            name == other.name &&
                            email == other.email &&
                            photoUrl == other.photoUrl &&
                            type == other.type
                    )
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (photoUrl?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}

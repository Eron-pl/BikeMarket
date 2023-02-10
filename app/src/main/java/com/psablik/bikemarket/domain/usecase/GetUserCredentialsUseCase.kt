package com.psablik.bikemarket.domain.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GetUserCredentialsUseCase {
    operator fun invoke(account: GoogleSignInClient): AuthCredential {
        GoogleAuthProvider.getCredential(account.apiKey, null).let { credential ->
            return credential
        }
    }
}
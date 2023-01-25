package com.psablik.bikemarket.presentation.login

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.domain.usecase.GetCredentialFromAccountUseCase
import com.psablik.bikemarket.domain.usecase.GetLoggedInStatusUseCase
import com.psablik.bikemarket.domain.usecase.SetLoggedInStatusUseCase
import com.psablik.bikemarket.domain.usecase.SignInWithCredentialUseCase
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val getCredentialFromAccount: GetCredentialFromAccountUseCase,
    private val signInWithCredential: SignInWithCredentialUseCase,
    private val getLoggedInStatus: GetLoggedInStatusUseCase,
    private val setLoggedStatus: SetLoggedInStatusUseCase,
    private val loggedStatusMapper: LoggedStatusMapper
) : ViewModel() {

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            if (loggedStatusMapper(getLoggedInStatus()) == LoggedStatus.LOGGED_IN) {
                _event.emit(
                    LoginEvent.LoggedIn
                )
            }
        }
    }

    fun getCredentialsFromActivityResult(result: ActivityResult): AuthCredential? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        return try {
            val account = task.getResult(ApiException::class.java)
            getCredentialFromAccount(account = account)
        } catch (e: ApiException) {
            Timber.e("Google sign in failed", e)
            null
        }
    }

    fun signIn(credential: AuthCredential) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if(signInWithCredential(credential = credential).isSuccess) {
                    setLoggedStatus(LoggedStatus.LOGGED_IN)
                    _event.emit(
                        LoginEvent.LoggedIn
                    )
                }
            } catch (e: Exception) {
                Timber.e(e.message)
            }
        }
    }

    fun getGoogleSingInIntent(): Intent = googleSignInClient.signInIntent
}
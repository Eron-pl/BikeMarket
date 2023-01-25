package com.psablik.bikemarket.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _event = MutableSharedFlow<SettingsEvent>()
    val event = _event.asSharedFlow()

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase()
            _event.emit(
                SettingsEvent.LoggedOut
            )
        }
    }

}

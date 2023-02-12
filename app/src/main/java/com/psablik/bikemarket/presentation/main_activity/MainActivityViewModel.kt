package com.psablik.bikemarket.presentation.main_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.GetUserTypeUseCase
import com.psablik.bikemarket.mapper.domain.UserType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getUserType: GetUserTypeUseCase
) : ViewModel() {
    var state by mutableStateOf<MainActivityViewState>(MainActivityViewState.Idle)
        private set

    init {
        state = MainActivityViewState.Loading

        viewModelScope.launch(IO) {
            try {
                val userType = getUserType()

                withContext(Main) {
                    state = MainActivityViewState.Loaded(
                        isAdmin = userType == UserType.ADMIN
                    )
                }
            } catch (e: Exception) {
                withContext(Main) {
                    state = MainActivityViewState.Error("Error loading main activity")
                }
            }
        }
    }
}
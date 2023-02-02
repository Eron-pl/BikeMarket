package com.psablik.bikemarket.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.GetBikeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBikes: GetBikeListUseCase
) : ViewModel() {

    var state by mutableStateOf<HomeViewState>(HomeViewState.Idle)
        private set

    init {
        state = HomeViewState.Loading

        viewModelScope.launch(IO) {
            val bikes = getBikes()

            delay(2000L)

            withContext(Main) {
                state = try {
                    HomeViewState.Loaded(
                        bikes = bikes
                    )
                } catch (e: Exception) {
                    HomeViewState.Error(
                        message = "Error loading data"
                    )
                }
            }
        }
    }

}

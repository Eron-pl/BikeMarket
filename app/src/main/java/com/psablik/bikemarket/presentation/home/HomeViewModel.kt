package com.psablik.bikemarket.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.psablik.bikemarket.domain.usecase.GetBikeListUseCase
import com.psablik.bikemarket.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun navigateToProduct(navController: NavController, id: String) {
        navController.navigate(
            route = Screen.Product.route
                .replace(
                    oldValue = Screen.PRODUCT_ID_ARG_KEY,
                    newValue = id
                )
        )
    }

}

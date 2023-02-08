package com.psablik.bikemarket.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.components.BikeListColumn
import com.psablik.bikemarket.presentation.components.ProgressIndicator
import com.psablik.bikemarket.presentation.ui.theme.B5
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxWidth()) {
        val bikeListState = rememberLazyListState()

        TitleSection()

        Spacer(Modifier.height(MaterialTheme.spacing.m))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state is HomeViewState.Loading) {
                ProgressIndicator()
            }

            if (state is HomeViewState.Loaded) {
                BikeListColumn(
                    state = bikeListState,
                    bikeList = state.bikes,
                    onClick = { id ->
                        viewModel.navigateToProduct(id = id, navController = navController)
                    }
                )
            }
        }
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Our bikes", // Todo: Strings
        style = H2,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )

    Spacer(Modifier.height(MaterialTheme.spacing.xs))

    Text(
        text = "Choose one that suits you best", // Todo: Strings
        style = B5,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )
}



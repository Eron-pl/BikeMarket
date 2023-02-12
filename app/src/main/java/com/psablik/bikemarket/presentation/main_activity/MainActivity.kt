package com.psablik.bikemarket.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.psablik.bikemarket.navigation.Navigation
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.components.BottomNavigationBar
import com.psablik.bikemarket.presentation.components.TopBar
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Background
import com.psablik.bikemarket.ui.theme.BikeMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BikeMarketTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val navController = rememberAnimatedNavController()
    val currentDestination = navController.currentBackStackEntry?.destination?.route

    var shouldShowBackButton by remember { // Todo: Export to state
        mutableStateOf(currentDestination == Screen.Settings.route)
    }

    var shouldShowTopBar by remember { // Todo: Export to state
        mutableStateOf(currentDestination != Screen.Login.route)
    }

    var shouldShowBottomNav by remember { // Todo: Export to state
        mutableStateOf(currentDestination != Screen.Login.route &&
                currentDestination != Screen.AdminPanel.route)
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        shouldShowBackButton =
            destination.route == Screen.Settings.route ||
                    destination.route == Screen.Product.route ||
                    destination.route == Screen.Payment.route // Todo: Pass to VM -> state -> UI

        shouldShowTopBar = destination.route != Screen.Login.route

        shouldShowBottomNav =
            destination.route != Screen.Login.route &&
                    destination.route != Screen.AdminPanel.route
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        if (shouldShowTopBar) {
            TopBar(
                navController = navController,
                shouldShowBackButton = shouldShowBackButton
            )
        }

        Navigation(
            modifier = Modifier
                .weight(1f),
            navController = navController
        )

        if (shouldShowBottomNav) {
            Surface(elevation = MaterialTheme.spacing.s) {
                BottomNavigationBar(
                    navController = navController
                )
            }
        }
    }
}

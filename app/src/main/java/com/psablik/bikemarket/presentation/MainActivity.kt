package com.psablik.bikemarket.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val currentDestination = navController.currentBackStackEntry?.destination?.route

    var shouldShowBackButton by remember { // Todo: Export to state
        mutableStateOf(currentDestination == Screen.Settings.route)
    }

    var shouldShowBottomNavAndTopBar by remember { // Todo: Export to state
        mutableStateOf(currentDestination != Screen.Login.route)
    }

    navController.addOnDestinationChangedListener() { _, destination, _ ->
        shouldShowBackButton =
            destination.route == Screen.Settings.route // Todo: Pass to VM -> state -> UI
        shouldShowBottomNavAndTopBar =
            destination.route != Screen.Login.route
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        if (shouldShowBottomNavAndTopBar) {
            TopBar(
                navController = navController,
                shouldShowBackButton = shouldShowBackButton
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.l))

        Navigation(
            modifier = Modifier
                .weight(1f),
            navController = navController
        )

        if (shouldShowBottomNavAndTopBar) {
            Surface(elevation = MaterialTheme.spacing.s) {
                BottomNavigationBar(
                    navController = navController
                )
            }
        }
    }
}

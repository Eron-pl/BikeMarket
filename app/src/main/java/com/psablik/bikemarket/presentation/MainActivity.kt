package com.psablik.bikemarket.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
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
import com.psablik.bikemarket.ui.theme.BikeMarketTheme

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

    navController.addOnDestinationChangedListener() { _, destination, _ ->
        shouldShowBackButton =
            destination.route == Screen.Settings.route // Todo: Pass to VM -> state -> UI
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        TopBar(
            navController = navController,
            shouldShowBackButton = shouldShowBackButton
        )

        Navigation(
            modifier = Modifier
                .weight(1f)
                .padding(MaterialTheme.spacing.s),
            navController = navController
        )

        BottomNavigationBar(
            navController = navController
        )
    }
}

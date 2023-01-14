package com.psablik.bikemarket.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.psablik.bikemarket.navigation.Navigation
import com.psablik.bikemarket.presentation.components.BottomNavigationBar
import com.psablik.bikemarket.ui.theme.BikeMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()

    BikeMarketTheme {
        Column(
            Modifier.fillMaxSize()
        ) {
            Navigation(
                modifier = Modifier.weight(1f),
                navController = navController
            )
            BottomNavigationBar(
                navController = navController
            )
        }
    }
}

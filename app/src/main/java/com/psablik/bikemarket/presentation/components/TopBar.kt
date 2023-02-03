package com.psablik.bikemarket.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.psablik.bikemarket.R
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.ui.theme.B5
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant

@Composable
fun TopBar(
    navController: NavController,
    shouldShowBackButton: Boolean
) {
    var backButtonVisibility by remember {
        mutableStateOf(false)
    }
    backButtonVisibility = shouldShowBackButton

    Surface(elevation = MaterialTheme.spacing.xs) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.m,
                    vertical = MaterialTheme.spacing.xxxs
                ),
        ) {

            AnimatedVisibility(
                visible = backButtonVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null // Todo
                    )
                }
            }

            Text(
                text = "BikeMarket",
                style = B5,
                color = Variant,
                modifier = Modifier.align(Alignment.Center)
            )

            IconButton(
                onClick = { navController.navigate(Screen.Settings.route) },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null // Todo
                )
            }
        }
    }
}


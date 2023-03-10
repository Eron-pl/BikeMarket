package com.psablik.bikemarket.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.ui.theme.B4
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant
import com.psablik.bikemarket.utils.LaunchOnce

@Composable
fun SettingsView(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController
) {

    LaunchedEffect(LaunchOnce) {
        viewModel.event.collect { event ->
            when (event) {
                is SettingsEvent.LoggedOut -> navController.navigate(Screen.Login.route)
            }
        }
    }

    Box {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
            TitleSection()
        }
        LogoutButton(
            onClick = { viewModel.logOut() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Settings", // Todo: Strings
        style = H2,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )

    Spacer(Modifier.height(MaterialTheme.spacing.xs))
}

@Composable
fun LogoutButton(onClick: () -> Unit, modifier: Modifier) {
    BaseButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.m)
    ) {
        Text(text = "Log out", style = B4) // Todo: String
    }
}
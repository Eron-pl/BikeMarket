package com.psablik.bikemarket.presentation.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.psablik.bikemarket.R
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.ui.theme.B3
import com.psablik.bikemarket.presentation.ui.theme.B7
import com.psablik.bikemarket.presentation.ui.theme.H1
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.utils.LaunchOnce

@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LaunchedEffect(LaunchOnce) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginEvent.LoggedIn -> navController.navigate(Screen.Home.route)
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        with(viewModel) {
            getCredentialsFromActivityResult(result = result)?.let { credential ->
                signIn(credential)
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(
            vertical = MaterialTheme.spacing.xxxl,
            horizontal = MaterialTheme.spacing.m
        )
    ) {

        Spacer(Modifier.height(MaterialTheme.spacing.s))

        Column {

            Text(
                text = stringResource(id = R.string.app_name),
                style = H1
            )


            Spacer(modifier = modifier.height(MaterialTheme.spacing.m))

            Text(
                text = stringResource(id = R.string.login_title_description),
                style = B7
            )
        }

        Image(
            modifier = modifier
                .aspectRatio(2.5f)
                .scale(2.5f)
                .rotate(-13f),
            painter = painterResource(id = R.drawable.bike),
            contentDescription = null
        )

        SignInButton(onClick = {
            with(viewModel) {
                getGoogleSingInIntent().let { intent ->
                    launcher.launch(intent)
                }
            }
        })
    }

}

@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    BaseButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = stringResource(R.string.login_sign_in_button_description),
            Modifier
                .height(MaterialTheme.spacing.xxl)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))

        Text(
            text = stringResource(id = R.string.login_sign_in_with_google_button),
            textAlign = TextAlign.Center,
            style = B3,
            color = MaterialTheme.colors.onBackground
        )
    }
}
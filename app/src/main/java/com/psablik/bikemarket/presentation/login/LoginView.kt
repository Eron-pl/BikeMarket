package com.psablik.bikemarket.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.psablik.bikemarket.R
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.navigation.isNotInScreen
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.ui.theme.B1
import com.psablik.bikemarket.presentation.ui.theme.B3
import com.psablik.bikemarket.presentation.ui.theme.B7
import com.psablik.bikemarket.presentation.ui.theme.H1
import com.psablik.bikemarket.presentation.ui.theme.spacing

@Composable
fun LoginView(
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(
            vertical = MaterialTheme.spacing.xxxl,
            horizontal = MaterialTheme.spacing.m
        )
    ) {

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

        SignInButton(onClick = { })
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
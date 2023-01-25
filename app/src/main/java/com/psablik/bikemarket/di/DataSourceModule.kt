package com.psablik.bikemarket.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteUserDataSource
import com.psablik.bikemarket.mapper.domain.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun localAuthenticationDataSource(
        @ApplicationContext context: Context
    ): LocalAuthenticationDataSource = LocalAuthenticationDataSource(
        context
    )

    @Provides
    @Singleton
    fun remoteAuthenticationDataSource(
        firebaseAuth: FirebaseAuth,
        googleSignInClient: GoogleSignInClient
    ): RemoteAuthenticationDataSource = RemoteAuthenticationDataSource(
        firebaseAuth = firebaseAuth,
        googleSignInClient = googleSignInClient
    )

    @Provides
    @Singleton
    fun remoteUserDataSource(
        firebaseAuth: FirebaseAuth,
        userMapper: UserMapper,
        firestore: FirebaseFirestore
    ): RemoteUserDataSource = RemoteUserDataSource(
        firebaseAuth = firebaseAuth,
        userMapper = userMapper,
        firestore = firestore
    )

}

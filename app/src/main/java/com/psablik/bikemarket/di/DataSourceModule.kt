package com.psablik.bikemarket.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
import com.psablik.bikemarket.infrastructure.remote.FirestoreDataSource
import com.psablik.bikemarket.infrastructure.remote.RemoteAuthenticationDataSource
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
        googleSignInClient: GoogleSignInClient,
        firestore: FirebaseFirestore,
        userMapper: UserMapper
    ): RemoteAuthenticationDataSource = RemoteAuthenticationDataSource(
        firebaseAuth = firebaseAuth,
        googleSignInClient = googleSignInClient,
        userMapper = userMapper,
        firestore = firestore
    )

    @Provides
    @Singleton
    fun firestoreDataSource(
        firestore: FirebaseFirestore
    ): FirestoreDataSource = FirestoreDataSource(
        firestore = firestore
    )

}

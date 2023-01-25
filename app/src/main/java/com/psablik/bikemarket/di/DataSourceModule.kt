package com.psablik.bikemarket.di

import android.content.Context
import com.psablik.bikemarket.infrastructure.local.LocalAuthenticationDataSource
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

}

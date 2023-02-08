package com.psablik.bikemarket.di

import android.content.Context
import android.content.res.Resources
import androidx.navigation.NavHostController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun resources(
        @ApplicationContext context: Context
    ): Resources = context.resources

}

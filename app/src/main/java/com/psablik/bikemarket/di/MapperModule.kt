package com.psablik.bikemarket.di

import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Reusable
    fun loggedStatusMapper(): LoggedStatusMapper = LoggedStatusMapper()

}
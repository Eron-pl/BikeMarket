package com.psablik.bikemarket.di

import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
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

    @Provides
    @Reusable
    fun userMapper(): UserMapper = UserMapper()

}
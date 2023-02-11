package com.psablik.bikemarket.di

import com.psablik.bikemarket.mapper.domain.BikeMapper
import com.psablik.bikemarket.mapper.domain.LoggedStatusMapper
import com.psablik.bikemarket.mapper.domain.UserMapper
import com.psablik.bikemarket.mapper.view.StatusOnViewMapper
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

    @Provides
    @Reusable
    fun bikeMapper(): BikeMapper = BikeMapper()

    @Provides
    @Reusable
    fun statusOnViewMapper(): StatusOnViewMapper = StatusOnViewMapper()

}
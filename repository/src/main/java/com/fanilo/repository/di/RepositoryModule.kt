package com.fanilo.repository.di

import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.repository.RestaurantRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRestaurantRepository(repository: RestaurantRepository): IRestaurantDataSource
}
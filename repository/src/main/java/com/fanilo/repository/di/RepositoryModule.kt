package com.fanilo.repository.di

import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.repository.RestaurantRepository
import dagger.Module

@Module
abstract class RepositoryModule {
    abstract fun provideRestaurantRepository(repository: RestaurantRepository): IRestaurantDataSource
}
package com.fanilo.restaurantfinder.di

import com.fanilo.android.IDaggerFactoryViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class DaggerFactoryModule {

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: DaggerViewModelFactory): IDaggerFactoryViewModel
}
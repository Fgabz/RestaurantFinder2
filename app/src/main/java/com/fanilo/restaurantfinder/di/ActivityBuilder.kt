package com.fanilo.restaurantfinder.di

import com.fanilo.core.annotation.PerActivity
import com.fanilo.home.MainActivity
import com.fanilo.home.di.MapFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            MapFragmentModule.Provider::class
        ]
    )
    abstract fun contributeMainActivityInjector(): MainActivity
}
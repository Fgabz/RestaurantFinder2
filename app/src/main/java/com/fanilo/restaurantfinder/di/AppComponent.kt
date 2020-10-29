package com.fanilo.restaurantfinder.di

import android.app.Application
import android.content.Context
import com.fanilo.core.annotation.PerApp
import com.fanilo.restaurantfinder.RestaurantFinderApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApp
@Component(
    modules = [
        AndroidInjectionModule::class,
        DaggerFactoryModule::class,
        PreferenceModule::class
    ]
)
interface AppComponent {

    fun inject(application: RestaurantFinderApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

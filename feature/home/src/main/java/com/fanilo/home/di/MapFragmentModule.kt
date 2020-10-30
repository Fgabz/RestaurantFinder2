package com.fanilo.home.di

import androidx.lifecycle.ViewModel
import com.fanilo.android.ViewModelKey
import com.fanilo.core.annotation.PerFragment
import com.fanilo.home.MapFragment
import com.fanilo.home.MapViewController
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MapFragmentModule {
    @Module

    abstract class Provider {
        @PerFragment
        @ContributesAndroidInjector(modules = [MapFragmentModule::class])
        abstract fun contributeMapperFragmentInjector(): MapFragment
    }

    @Binds
    @IntoMap
    @ViewModelKey(MapViewController::class)
    abstract fun bindRepoDetailViewModel(viewModel: MapViewController): ViewModel
}
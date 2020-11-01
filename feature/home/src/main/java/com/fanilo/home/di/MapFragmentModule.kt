package com.fanilo.home.di

import androidx.lifecycle.ViewModel
import com.fanilo.android.ViewModelKey
import com.fanilo.core.annotation.PerFragment
import com.fanilo.domain.FetchOnMapReadyUseCase
import com.fanilo.domain.FetchRestaurantUseCase
import com.fanilo.home.IMapController
import com.fanilo.home.IMapPresenter
import com.fanilo.home.MapController
import com.fanilo.home.MapFragment
import com.fanilo.home.MapInteractor
import com.fanilo.home.MapPresenter
import com.fanilo.home.MapViewController
import com.fanilo.home.viewstate.IMarkerViewStateMapper
import com.fanilo.home.viewstate.MarkerViewStateMapper
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
    abstract fun provideOnMapReadyCase(interactor: MapInteractor): FetchOnMapReadyUseCase

    @Binds
    abstract fun provideFetchUseCase(interactor: MapInteractor): FetchRestaurantUseCase

    @Binds
    abstract fun provideMapController(controller: MapController): IMapController

    @Binds
    abstract fun providePresenter(presenter: MapPresenter): IMapPresenter

    @Binds
    abstract fun provideViewStateMapper(mapper: MarkerViewStateMapper): IMarkerViewStateMapper

    @Binds
    @IntoMap
    @ViewModelKey(MapViewController::class)
    abstract fun bindRepoDetailViewModel(viewModel: MapViewController): ViewModel
}
package com.fanilo.service.mapper.di

import com.fanilo.service.mapper.IRemoteFoursquareResponseMapper
import com.fanilo.service.mapper.RemoteFoursquareResponseMapper
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

    @Binds
    abstract fun provideRemoteRestaurantMapper(mapper: RemoteFoursquareResponseMapper): IRemoteFoursquareResponseMapper

}
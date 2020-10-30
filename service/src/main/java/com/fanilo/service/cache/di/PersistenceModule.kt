package com.fanilo.service.cache.di

import com.fanilo.core.annotation.PerApp
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.ICacheRestaurantService
import com.fanilo.service.cache.CacheRestaurant
import com.fanilo.service.cache.CacheRestaurantService
import com.fanilo.service.cache.ICacheRestaurant
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Module(includes = [PersistenceModule.AbstractionModule::class])
class PersistenceModule {

    @Module
    internal abstract class AbstractionModule {
        @Binds
        abstract fun provideCacheRestaurant(cache: CacheRestaurant): ICacheRestaurant

        @Binds
        abstract fun provideCacheRestaurantService(cacheService: CacheRestaurantService): ICacheRestaurantService
    }

    @PerApp
    @Provides
    fun providesRestaurantCache(): ConcurrentMap<String, Restaurant?> {
        return ConcurrentHashMap()
    }
}
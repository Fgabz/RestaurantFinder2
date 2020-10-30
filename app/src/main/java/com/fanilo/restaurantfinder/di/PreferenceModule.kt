package com.fanilo.restaurantfinder.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.fanilo.android.StringPreference
import com.fanilo.core.annotation.FoursquareEndpoint
import com.fanilo.core.annotation.MapboxPublicToken
import com.fanilo.core.annotation.PerApp
import com.fanilo.domain.IPreference
import com.fanilo.restaurantfinder.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    @PerApp
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return return app.getSharedPreferences("com.fanilo.restaurantfinder.prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @PerApp
    @FoursquareEndpoint
    internal fun provideEndpoint(preferences: SharedPreferences): IPreference<String> {
        return StringPreference("foursquare_endpoint", preferences, "https://api.foursquare.com")
    }

    @Provides
    @PerApp
    @MapboxPublicToken
    internal fun providePublicAccessToken(preferences: SharedPreferences): IPreference<String> {
        return StringPreference("mapbox_public_access", preferences, BuildConfig.MAPBOX_PUBLIC_ACCES_TOKEN)
    }
}
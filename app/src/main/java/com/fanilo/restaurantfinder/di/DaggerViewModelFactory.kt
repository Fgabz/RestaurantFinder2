package com.fanilo.restaurantfinder.di

import androidx.lifecycle.ViewModel
import com.fanilo.android.IDaggerFactoryViewModel
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelFactory @Inject constructor(
    private val viewModelsMap: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>
) : IDaggerFactoryViewModel {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return requireNotNull(viewModelsMap[modelClass]).get() as T
    }
}
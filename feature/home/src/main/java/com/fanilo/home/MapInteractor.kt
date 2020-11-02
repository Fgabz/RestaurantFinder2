package com.fanilo.home

import androidx.annotation.VisibleForTesting
import com.fanilo.core.annotation.PerFragment
import com.fanilo.core.annotation.extension.contains
import com.fanilo.domain.FetchOnMapReadyUseCase
import com.fanilo.domain.FetchRestaurantUseCase
import com.fanilo.domain.StartCoordinateEvenDebouncerUseCase
import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.entity.onFailure
import com.fanilo.entity.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import timber.log.Timber
import javax.inject.Inject

@PerFragment
class MapInteractor @Inject constructor(
    private val restaurantDataSource: IRestaurantDataSource,
    private val presenter: IMapPresenter
) : FetchOnMapReadyUseCase, FetchRestaurantUseCase, StartCoordinateEvenDebouncerUseCase {

    @VisibleForTesting
    val eventChannel: Channel<Pair<LatitudeLongitudeBounds, LatitudeLongitude>> = Channel()
    private lateinit var availabilityFlow: Flow<Pair<LatitudeLongitudeBounds, LatitudeLongitude>>

    //Debouncing to avoid to do too many request while the user is panning the map
    override suspend fun startEventCoordinateEventDebouncer() {
        if (!::availabilityFlow.isInitialized) {
            availabilityFlow = eventChannel.consumeAsFlow()

            availabilityFlow
                .debounce(EVENT_DELAY_IN_MS)
                .collect {
                    fetchRestaurantCollector(it.first, it.second)
                }
        }
    }

    @VisibleForTesting
    suspend fun fetchRestaurantCollector(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) {
        restaurantDataSource.getCachedRestaurant().onSuccess {
            val cachedResult = it.filter { restaurant ->
                cameraBounds.contains(restaurant.location.LatitudeLongitude)
            }
            presenter.displayRestaurant(cachedResult)
        }

        restaurantDataSource.fetchNearRestaurant("${latitudeLongitude.latitude}, ${latitudeLongitude.longitude}").onSuccess {
            presenter.displayRestaurant(it)
        }.onFailure { error, message, _ ->
            Timber.e(error, message)
        }
    }

    override suspend fun fetchRestaurant(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) {
        eventChannel.send(Pair(cameraBounds, latitudeLongitude))
    }

    private companion object {
        const val EVENT_DELAY_IN_MS = 1000L
    }
}
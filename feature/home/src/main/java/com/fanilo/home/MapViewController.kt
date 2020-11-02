package com.fanilo.home

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fanilo.android.ViewController
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.home.viewstate.MarkerViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewController @Inject constructor(
    private val presenter: IMapPresenter,
    private val controller: IMapController
) : ViewController(), IMapView {

    private val _liveDataRestaurantViewState = MutableLiveData<List<MarkerViewState>>()

    val liveDataRestaurantViewState: LiveData<List<MarkerViewState>> = _liveDataRestaurantViewState

    override fun onCreate() {
        presenter.onAttachView(this)
    }

    override fun onViewReady(extras: Bundle?) = viewModelScope.launch(Dispatchers.IO) {
        controller.onViewReady()
    }

    override fun onViewFinished() {
        super.onViewFinished()
        presenter.onDetachView()
    }

    override fun displayRestaurant(markerList: List<MarkerViewState>) {
        _liveDataRestaurantViewState.postValue(markerList)
    }

    fun onMapReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) = viewModelScope.launch(Dispatchers.IO) {
        controller.onMapReady(cameraBounds, latitudeLongitude)
    }

    fun onLoadMoreRestaurant(bounds: LatitudeLongitudeBounds, cameraPosition: LatitudeLongitude) = viewModelScope.launch(Dispatchers.IO) {
       controller.onLoadMoreRestaurant(bounds, cameraPosition)
    }
}
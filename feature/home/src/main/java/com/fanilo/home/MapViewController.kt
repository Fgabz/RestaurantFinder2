package com.fanilo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.home.viewstate.MarkerViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewController @Inject constructor(
    private val presenter: IMapPresenter,
    private val controller: IMapController
) : ViewModel(), IMapView {

    private val _liveDataRestaurantViewState = MutableLiveData<List<MarkerViewState>>()

    val liveDataRestaurantViewState: LiveData<List<MarkerViewState>> = _liveDataRestaurantViewState

    fun onCreate() {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
        super.onCleared()
    }

    override fun displayRestaurant(markerList: List<MarkerViewState>) {
        _liveDataRestaurantViewState.postValue(markerList)
    }

    fun onViewReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) = viewModelScope.launch(Dispatchers.IO) {
        controller.onViewReady(cameraBounds, latitudeLongitude)
    }
}
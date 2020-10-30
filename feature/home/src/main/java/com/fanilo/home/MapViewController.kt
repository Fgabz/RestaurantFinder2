package com.fanilo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewController @Inject constructor(
    private val presenter: IMapPresenter,
    private val controller: IMapController
) : ViewModel(), IMapView {

    fun onCreate() {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
        super.onCleared()
    }

    fun onViewReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) = viewModelScope.launch(Dispatchers.IO) {
        controller.onViewReady(cameraBounds, latitudeLongitude)
    }
}
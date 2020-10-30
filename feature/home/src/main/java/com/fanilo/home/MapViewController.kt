package com.fanilo.home

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MapViewController @Inject constructor(
    private val presenter: IMapPresenter
) : ViewModel(), IMapView {

    fun onCreate() {
        presenter.onAttachView(this)
    }

    override fun onCleared() {
        presenter.onDetachView()
        super.onCleared()
    }
}
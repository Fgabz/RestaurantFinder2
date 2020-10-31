package com.fanilo.home

import com.fanilo.android.BaseView
import com.fanilo.home.viewstate.MarkerViewState

interface IMapView : BaseView {
    fun displayRestaurant(markerList: List<MarkerViewState>)
}
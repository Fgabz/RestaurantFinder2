package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import javax.inject.Inject

@PerFragment
class MapPresenter @Inject constructor() : IMapPresenter {
    override var view: IMapView? = null
}
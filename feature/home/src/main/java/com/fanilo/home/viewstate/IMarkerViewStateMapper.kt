package com.fanilo.home.viewstate

import com.fanilo.entity.restaurant.Restaurant

interface IMarkerViewStateMapper {
    fun mapEntityToViewState(entity: Restaurant): MarkerViewState
}
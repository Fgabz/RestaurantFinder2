package com.fanilo.service.webservice

import com.fanilo.service.api.model.RemoteRestaurantResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IFoursquareWebService {

    @GET("/v2/venues/search?categoryId=4d4b7105d754a06374d81259")
    suspend fun fetchRestaurant(@Query("ll") coordinate: String): Response<RemoteRestaurantResponse>
}
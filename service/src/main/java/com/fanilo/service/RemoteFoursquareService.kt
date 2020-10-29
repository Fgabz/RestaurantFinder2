package com.fanilo.service

import com.fanilo.entity.Answer
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.IRemoteFoursquareService
import com.fanilo.service.api.model.RemoteRestaurantResponse
import com.fanilo.service.api.throwHttpException
import com.fanilo.service.api.throwIOException
import com.fanilo.service.mapper.IRemoteFoursquareResponseMapper
import com.fanilo.service.webservice.IFoursquareWebService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteFoursquareService @Inject constructor(
    private val webService: IFoursquareWebService,
    private val mapper: IRemoteFoursquareResponseMapper
) : IRemoteFoursquareService {

    override suspend fun fetchNearRestaurant(coordinate: String): Answer<List<Restaurant>> {
        val response: Response<RemoteRestaurantResponse>

        try {
            response = webService.fetchRestaurant(coordinate)
        } catch (e: IOException) {
            return e.throwIOException()
        }

        response.body()?.let { result ->
            return Answer.Success(result.response.venues.map { mapper.remoteToEntity(it) })
        } ?: run {
            return response.throwHttpException()
        }
    }
}
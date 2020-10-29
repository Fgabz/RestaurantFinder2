package com.fanilo.service.api

import com.fanilo.service.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val authorizedUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("client_secret", BuildConfig.FOURSQUARE_CLIENT_SECRET)
            .addQueryParameter("client_id", BuildConfig.FOURSQUARE_CLIENT_ID)
            .addQueryParameter("v", "20201231")
            .build()

        val newRequestBuilder = original.newBuilder()
            .url(authorizedUrl)
            .header(HEADER_ACCEPT_NAME, HEADER_ACCEPT_VALUE)



        return chain.proceed(newRequestBuilder.build())
    }

    companion object {
        const val HEADER_ACCEPT_NAME = "Accept"
        const val HEADER_ACCEPT_VALUE = "application/json"
    }
}
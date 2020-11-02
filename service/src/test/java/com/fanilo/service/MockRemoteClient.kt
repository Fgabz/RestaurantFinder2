package com.fanilo.service

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MockRemoteClient(private val mockWebServer: MockWebServer) {

    fun <T : Any> get(clazz: Class<T>): T {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .readTimeout(150, TimeUnit.MILLISECONDS)
                    .connectTimeout(150, TimeUnit.MILLISECONDS)
                    .build()
            )
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(clazz)
    }
}

fun MockWebServer.prefetchSuccess(mockPath: String) {
    enqueue(MockResponse().setResponseCode(200).setBody(Buffer().readFrom(javaClass.classLoader!!.getResourceAsStream(mockPath))))
}

fun MockWebServer.prefetchError(responseCode: Int, mockPath: String) {
    enqueue(MockResponse().setResponseCode(responseCode).setBody(Buffer().readFrom(javaClass.classLoader!!.getResourceAsStream(mockPath))))
}
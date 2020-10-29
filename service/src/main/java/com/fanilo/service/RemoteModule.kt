package com.fanilo.service

import com.fanilo.core.annotation.PerApp
import com.fanilo.repository.service.IRemoteFoursquareService
import com.fanilo.service.api.AuthorizationInterceptor
import com.fanilo.service.webservice.IFoursquareWebService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RemoteModule.AbstractionModule::class])
class RemoteModule {
    @Module
    abstract class AbstractionModule {
        @Binds
        abstract fun provideAuthorizationInterceptor(interceptor: AuthorizationInterceptor): Interceptor

        @Binds
        abstract fun provideRemoteGithubService(remoteGithubService: RemoteFoursquareService): IRemoteFoursquareService
    }

    @PerApp
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @PerApp
    @Provides
    fun providesOkHttpClient(authorizationInterceptor: AuthorizationInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(12, TimeUnit.SECONDS)

        httpClient
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)

        return httpClient.build()
    }

    @PerApp
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @PerApp
    @Provides
    fun providesRemoteFoursquareWebservice(retrofit: Retrofit): IFoursquareWebService {
        return retrofit.create(IFoursquareWebService::class.java)
    }
}
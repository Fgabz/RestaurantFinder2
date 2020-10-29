package com.fanilo.service

import com.fanilo.service.api.AuthorizationInterceptor
import com.fanilo.service.webservice.IFoursquareWebService
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor

@Module(includes = [RemoteModule.AbstractionModule::class])
class RemoteModule {
    @Module
    abstract class AbstractionModule {
        @Binds
        abstract fun provideAuthorizationInterceptor(interceptor: AuthorizationInterceptor): Interceptor

        @Binds
        abstract fun provideRemoteGithubService(remoteGithubService: RemoteFoursquareService): IFoursquareWebService
    }
}
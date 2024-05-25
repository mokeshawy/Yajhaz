package com.mycash.yajhaz.core.di.network_module.services_module

import com.mycash.yajhaz.core.di.service.AuthServices
import com.mycash.yajhaz.core.di.service.HomeServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideAuthServices(retrofit: Retrofit): AuthServices =
        retrofit.create(AuthServices::class.java)


    @Provides
    @Singleton
    fun provideHomeServices(retrofit: Retrofit): HomeServices =
        retrofit.create(HomeServices::class.java)
}
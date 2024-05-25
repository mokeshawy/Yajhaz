package com.mycash.yajhaz.core.di.network_module.retrofit_module

import android.content.Context
import com.google.gson.Gson
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.token_utils.TokenHandler
import com.pluto.plugins.network.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Provides   /* provide gson converter */
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }


    @Provides
    @Named("AuthInterceptor")
    fun provideAuthInterceptor(tokenHandler: TokenHandler): Interceptor =
        Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            tokenHandler.getToken()?.let { newBuilder.addHeader("Authorization", "Bearer $it") }
            newBuilder.build().let { chain.proceed(it) }
        }

    @Provides
    fun providesNetLoggingInterceptor() = NetLoggingInterceptor()

    @Provides
    @Named("LoggingInterceptor")
    @Singleton
    fun httpLoggingInterceptor() = HttpLoggingInterceptor { message ->
        Timber.tag("OkHttp").d(message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun client(
        @Named("AuthInterceptor")
        authInterceptor: Interceptor,
        @Named("LoggingInterceptor")
        loggingInterceptor: Interceptor,
        netLoggingInterceptor: NetLoggingInterceptor
    ): OkHttpClient = OkHttpClient().newBuilder().run {
        addInterceptor(authInterceptor)
        addInterceptor(loggingInterceptor)
        addInterceptor(netLoggingInterceptor)
        addInterceptor(PlutoInterceptor())
        connectTimeout(2, TimeUnit.MINUTES)
        readTimeout(2, TimeUnit.MINUTES)
        build()
    }


    /* operation work of api using retrofit */
    @Singleton
    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
}
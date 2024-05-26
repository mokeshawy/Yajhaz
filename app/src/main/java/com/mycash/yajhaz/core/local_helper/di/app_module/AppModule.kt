package com.mycash.yajhaz.core.local_helper.di.app_module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.mycash.yajhaz.core.local_helper.LocaleHelper
import com.mycash.yajhaz.core.storage_manager.SharedPreferencesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocalHelper(sharedPreferencesManager: SharedPreferencesManager) =
        LocaleHelper(sharedPreferencesManager)

}
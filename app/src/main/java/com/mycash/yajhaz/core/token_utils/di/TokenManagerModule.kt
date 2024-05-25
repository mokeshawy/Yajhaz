package com.mycash.yajhaz.core.token_utils.di

import com.mycash.yajhaz.core.storage_manager.StorageManager
import com.mycash.yajhaz.core.storage_manager.di.StorageManagerModule.ENCRYPTED_SHARED_PREFERENCE
import com.mycash.yajhaz.core.token_utils.TokenHandler
import com.mycash.yajhaz.core.token_utils.TokensManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenManagerModule {

    @Provides
    @Singleton
    fun provideTokenManager(
        @Named(ENCRYPTED_SHARED_PREFERENCE)
        storageManager: StorageManager
    ): TokenHandler = TokensManager(storageManager)
}
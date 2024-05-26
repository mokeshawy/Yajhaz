package com.mycash.yajhaz.features.fragment.login.di

import com.mycash.yajhaz.core.di.service.AuthServices
import com.mycash.yajhaz.features.fragment.login.data.repository.LoginRepositoryImpl
import com.mycash.yajhaz.features.fragment.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {


    @Provides
    fun provideLoginRepository(authServices: AuthServices): LoginRepository =
        LoginRepositoryImpl(authServices)
}
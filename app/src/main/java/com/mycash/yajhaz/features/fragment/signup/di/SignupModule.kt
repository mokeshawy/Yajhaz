package com.mycash.yajhaz.features.fragment.signup.di

import com.mycash.yajhaz.core.di.service.AuthServices
import com.mycash.yajhaz.features.fragment.signup.data.repository.ClientRegisterRepositoryImpl
import com.mycash.yajhaz.features.fragment.signup.domain.repository.ClientRegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object SignupModule {


    @Provides
    fun provideClientRegisterRepository(authServices: AuthServices): ClientRegisterRepository =
        ClientRegisterRepositoryImpl(authServices)
}
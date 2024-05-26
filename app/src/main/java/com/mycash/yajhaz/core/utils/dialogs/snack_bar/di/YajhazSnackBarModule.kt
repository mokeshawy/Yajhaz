package com.mycash.yajhaz.core.utils.dialogs.snack_bar.di

import android.app.Activity
import com.mycash.yajhaz.core.utils.dialogs.snack_bar.YajhazSnackBarBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class YajhazSnackBarModule {

    @Provides
    fun provideYabraaSnackBarBuilder(activity: Activity) = YajhazSnackBarBuilder(activity)
}
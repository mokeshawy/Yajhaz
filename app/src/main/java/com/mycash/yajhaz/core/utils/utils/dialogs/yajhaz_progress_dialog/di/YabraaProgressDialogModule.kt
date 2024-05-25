package com.mycash.yajhaz.core.utils.utils.dialogs.yajhaz_progress_dialog.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import com.mycash.yajhaz.core.utils.utils.dialogs.yajhaz_progress_dialog.YajhazProgressDialog

@Module
@InstallIn(ActivityComponent::class)
object YabraaProgressDialogModule {

    @Provides
    fun provideYabraaProgressDialog(activity: Activity) = YajhazProgressDialog(activity)
}
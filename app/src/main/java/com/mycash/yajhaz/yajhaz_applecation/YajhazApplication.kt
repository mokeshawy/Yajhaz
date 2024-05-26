package com.mycash.yajhaz.yajhaz_applecation

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mycash.yajhaz.BuildConfig
import com.mycash.yajhaz.core.local_helper.LocaleHelper
import com.mycash.yajhaz.core.storage_manager.StorageManager
import com.mycash.yajhaz.core.storage_manager.di.StorageManagerModule
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidApp
class YajhazApplication : Application() {


    @Inject
    @Named(StorageManagerModule.SHARED_PREFERENCE)
    lateinit var storageManager: StorageManager

    private val firebaseCrashlytics by lazy { FirebaseCrashlytics.getInstance() }
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        LocaleHelper(storageManager).init(this)
        plantTimberTrees()
        initRemoteDebugger()
    }


    private fun plantTimberTrees() {
        Timber.plant(CrashReportingTree())
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.plant(RemoteDebuggerTree())
        }
    }


    private fun initRemoteDebugger() {
        if (!BuildConfig.DEBUG) return
        val remoteDebugger =
            AndroidRemoteDebugger.Builder(applicationContext).disableInternalLogging()
                .port(getRemoteDebuggerPort()).build()
        AndroidRemoteDebugger.init(remoteDebugger)
    }

    private fun getRemoteDebuggerPort() = 8087


    private inner class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            firebaseCrashlytics.log(message)
        }
    }


    private inner class RemoteDebuggerTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            AndroidRemoteDebugger.Log.log(priority, tag, message, t)
        }
    }

}
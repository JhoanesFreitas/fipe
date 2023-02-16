package com.cajusoftware.fipe

import android.app.Application
import android.os.Build
import com.cajusoftware.fipe.di.AppContainer
import com.cajusoftware.fipe.di.Container
import com.cajusoftware.fipe.utils.NetworkUtils.networkConnectivity

class FipeApplication : Application() {
    lateinit var appContainer: Container

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            networkConnectivity(this)
        }
    }
}
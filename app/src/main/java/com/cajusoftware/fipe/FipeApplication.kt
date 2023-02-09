package com.cajusoftware.fipe

import android.app.Application
import com.cajusoftware.fipe.di.AppContainer
import com.cajusoftware.fipe.di.Container

class FipeApplication : Application() {
    lateinit var appContainer: Container

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
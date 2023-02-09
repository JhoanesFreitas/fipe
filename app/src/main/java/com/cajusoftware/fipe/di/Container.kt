package com.cajusoftware.fipe.di

import android.content.Context
import com.cajusoftware.fipe.data.database.FipeDatabase
import com.cajusoftware.fipe.data.repositories.VehicleRepository
import com.cajusoftware.fipe.data.repositories.VehicleRepositoryImpl

interface Container {
    val vehicleRepository: VehicleRepository
}

class AppContainer(private val context: Context) : Container {
    override val vehicleRepository: VehicleRepository by lazy {
        VehicleRepositoryImpl(FipeDatabase.getDatabase(context).vehicleDao())
    }
}
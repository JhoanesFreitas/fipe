package com.cajusoftware.fipe.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cajusoftware.fipe.FipeApplication
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer { VehicleBrandViewModel(fipeApplication().appContainer.vehicleBrandRepository) }
    }
}

fun CreationExtras.fipeApplication(): FipeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FipeApplication)
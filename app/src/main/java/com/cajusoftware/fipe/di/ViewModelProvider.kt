package com.cajusoftware.fipe.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cajusoftware.fipe.FipeApplication
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer { VehicleBrandViewModel(inventoryApplication().appContainer.vehicleBrandRepository) }
    }
}

fun CreationExtras.inventoryApplication(): FipeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FipeApplication)
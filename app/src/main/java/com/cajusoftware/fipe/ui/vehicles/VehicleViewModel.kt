package com.cajusoftware.fipe.ui.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cajusoftware.fipe.data.domain.Vehicle
import com.cajusoftware.fipe.data.repositories.models.VehicleRepository

class VehicleViewModel(private val repository: VehicleRepository) : ViewModel() {

    val vehicleBrands: LiveData<List<Vehicle>> = repository.vehicles.asLiveData()
}
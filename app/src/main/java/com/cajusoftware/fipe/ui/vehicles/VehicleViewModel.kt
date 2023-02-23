package com.cajusoftware.fipe.ui.vehicles

import androidx.lifecycle.*
import com.cajusoftware.fipe.data.domain.Historic
import com.cajusoftware.fipe.data.domain.Vehicle
import com.cajusoftware.fipe.data.repositories.models.VehicleRepository
import com.cajusoftware.fipe.utils.NetworkUtils.exceptionHandler
import kotlinx.coroutines.launch

class VehicleViewModel(
    private val repository: VehicleRepository
) : ViewModel() {

    val vehicleBrands: LiveData<List<Vehicle>> = repository.vehicles.asLiveData()

    private val _vehicle: MutableLiveData<Vehicle> = MutableLiveData()
    val vehicle: LiveData<Vehicle>
        get() = _vehicle

    private val _historic: MutableLiveData<Historic?> = MutableLiveData()
    val historic: LiveData<Historic?>
        get() = _historic

    fun fetchVehicleYears(brandCode: String, vehicleCode: String) {
        viewModelScope.launch(exceptionHandler) {
            repository.fetchModelYears(brandCode, vehicleCode)
        }
    }

    fun fetchVehicleLastYear(
        brandCode: String,
        vehicleCode: String,
        brandName: String,
        vehicleName: String
    ) {
        viewModelScope.launch(exceptionHandler) {
            getVehicleYears(brandCode, vehicleCode, brandName, vehicleName)
        }
    }

    private suspend fun getVehicleYears(
        brandCode: String,
        vehicleCode: String,
        brandName: String,
        vehicleName: String
    ) {
        repository.getVehicleYears(brandCode, vehicleCode).collect {
            it.firstOrNull()?.let { modelYear ->
                repository.fetchVehicle(brandCode, vehicleCode, modelYear.yearCode)
                repository.getVehicle(brandName, vehicleName, modelYear.yearToSearch())
                    .collect { vehicle ->
                        vehicle?.let {
                            fetchHistory(vehicle.fipeCode, vehicle.modelYear.toString())
                            _vehicle.postValue(vehicle)
                        }
                    }
            }
        }
    }

    private fun fetchHistory(fipeCode: String, year: String) {
        viewModelScope.launch {
            repository.fetchPricesByYears(fipeCode, year)
        }

        viewModelScope.launch {
            repository.historic.collect {
                _historic.postValue(it)
            }
        }
    }
}
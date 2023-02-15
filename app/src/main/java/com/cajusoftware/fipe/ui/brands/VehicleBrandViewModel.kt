package com.cajusoftware.fipe.ui.brands

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandModel
import com.cajusoftware.fipe.data.repositories.brands.VehicleBranchRepository
import kotlinx.coroutines.launch

class VehicleBrandViewModel(private val repository: VehicleBranchRepository) : ViewModel() {

    val vehicleBrands: LiveData<List<Brand>> = repository.vehicleBrands.asLiveData()

    var currentBrandSelected: String = "23"

    val brandModels: LiveData<List<BrandModel>> = repository.brandModels.asLiveData()

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            repository.getAllVehicleBrands()
            repository.getAllBrandModels(currentBrandSelected)
        }
    }

    fun getBrandName(brandNumber: String): LiveData<String> {
        return repository.getBrandName(brandNumber).asLiveData()
    }
}
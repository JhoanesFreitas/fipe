package com.cajusoftware.fipe.ui.brands

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandModel
import com.cajusoftware.fipe.data.repositories.brands.VehicleBranchRepository
import com.cajusoftware.fipe.utils.NetworkUtils.exceptionHandler
import kotlinx.coroutines.launch

class VehicleBrandViewModel(private val repository: VehicleBranchRepository) : ViewModel() {

    private var brandNumber: String = ""

    val vehicleBrands: LiveData<List<Brand>> = repository.vehicleBrands.asLiveData()

    val brandModels: LiveData<List<BrandModel>> =
        repository.getBrandsModels(brandNumber).asLiveData()

    val firstBrand: LiveData<Brand?> = repository.firstVehicleBrands.asLiveData()

    init {
        getAllBrands()
    }

    private fun getAllBrands() {
        viewModelScope.launch(exceptionHandler) {
            repository.getAllVehicleBrands()
        }
    }

    fun fetchBrandsModels(brandNumber: String) {
        this.brandNumber = brandNumber
        viewModelScope.launch(exceptionHandler) {
            repository.fetchBrandsModels(brandNumber)
        }
    }

    fun getBrandsModels(brandNumber: String?) =
        brandNumber?.let { repository.getBrandsModels(it).asLiveData() }

    fun getBrandName(brandNumber: String): LiveData<String> {
        return repository.getBrandName(brandNumber).asLiveData()
    }
}
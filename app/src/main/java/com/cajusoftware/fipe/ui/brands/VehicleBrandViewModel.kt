package com.cajusoftware.fipe.ui.brands

import androidx.lifecycle.*
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandsModel
import com.cajusoftware.fipe.data.repositories.brands.VehicleBranchRepository
import com.cajusoftware.fipe.utils.NetworkUtils.exceptionHandler
import kotlinx.coroutines.launch

class VehicleBrandViewModel(private val repository: VehicleBranchRepository) : ViewModel() {

    private var brandNumber: String = ""

    val vehicleBrands: LiveData<List<Brand>> = repository.vehicleBrands.asLiveData()

    val brandsModels: LiveData<List<BrandsModel>> =
        repository.getBrandsModels(brandNumber).asLiveData()

    val firstBrand: LiveData<Brand?> = repository.firstVehicleBrands.asLiveData()

    private val _isModelLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isModelLoading: LiveData<Boolean>
        get() = _isModelLoading

    private val _isBrandLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isBrandLoading: LiveData<Boolean>
        get() = _isBrandLoading

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

    fun setModelLoading(isLoading: Boolean) {
        _isModelLoading.postValue(isLoading)
    }

    fun setBrandLoading(isLoading: Boolean) {
        _isBrandLoading.postValue(isLoading)
    }
}
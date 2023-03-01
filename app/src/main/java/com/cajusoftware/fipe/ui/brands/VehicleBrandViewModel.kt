package com.cajusoftware.fipe.ui.brands

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandsModel
import com.cajusoftware.fipe.data.repositories.brands.VehicleBranchRepository
import com.cajusoftware.fipe.utils.NetworkUtils.exceptionHandler
import com.cajusoftware.fipe.utils.RetryCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val FIRST_BRAND_NUMBER = "1"

class VehicleBrandViewModel(private val repository: VehicleBranchRepository) : ViewModel() {

    private var oldJob: Job? = null

    val vehicleBrands: LiveData<List<Brand>> = repository.vehicleBrands.asLiveData()

    private val _brandModels: MutableLiveData<PagingData<BrandsModel>?> = MutableLiveData()
    val brandsModels: LiveData<PagingData<BrandsModel>?>
        get() = _brandModels

    private val _isModelLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isModelLoading: LiveData<Boolean>
        get() = _isModelLoading

    private val _isBrandLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isBrandLoading: LiveData<Boolean>
        get() = _isBrandLoading

    val scope: CoroutineScope
        get() = viewModelScope

    init {
        getAllBrands()
        getBrandsModels(FIRST_BRAND_NUMBER)
    }

    private fun getAllBrands() {
        viewModelScope.launch(exceptionHandler + RetryCallback { getAllBrands() }) {
            repository.getAllVehicleBrands()
        }
    }

    fun fetchBrandsModels(brandNumber: String) {
        viewModelScope.launch(exceptionHandler + RetryCallback { fetchBrandsModels(brandNumber) }) {
            repository.fetchBrandsModels(brandNumber)
        }
    }

    fun getBrandsModels(brandNumber: String) {
        setModelLoading(true)
        oldJob?.cancel()
        oldJob = viewModelScope.launch {
            repository.getPagingBrandsModels(brandNumber)
                .cancellable()
                .cachedIn(viewModelScope)
                .collectLatest { _brandModels.postValue(it) }
        }
    }

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
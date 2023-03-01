package com.cajusoftware.fipe.data.repositories.brands

import androidx.paging.PagingData
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandsModel
import kotlinx.coroutines.flow.Flow

interface VehicleBranchRepository {
    val vehicleBrands: Flow<List<Brand>>
    val firstVehicleBrands: Flow<Brand?>
    val brandsModels: Flow<List<BrandsModel>>
    suspend fun getAllVehicleBrands()
    suspend fun fetchBrandsModels(brandNumber: String)
    fun getBrandName(brandNumber: String): Flow<String>
    fun getBrandsModels(brandNumber: String): Flow<List<BrandsModel>>
    fun getPagingBrandsModels(brandNumber: String): Flow<PagingData<BrandsModel>>
}
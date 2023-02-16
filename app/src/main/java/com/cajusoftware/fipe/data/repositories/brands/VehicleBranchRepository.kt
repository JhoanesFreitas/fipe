package com.cajusoftware.fipe.data.repositories.brands

import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandModel
import kotlinx.coroutines.flow.Flow

interface VehicleBranchRepository {
    val vehicleBrands: Flow<List<Brand>>
    val firstVehicleBrands: Flow<Brand?>
    val brandModels: Flow<List<BrandModel>>
    suspend fun getAllVehicleBrands()
    suspend fun fetchBrandsModels(brandNumber: String)
    fun getBrandName(brandNumber: String): Flow<String>
    fun getBrandsModels(brandNumber: String): Flow<List<BrandModel>>
}
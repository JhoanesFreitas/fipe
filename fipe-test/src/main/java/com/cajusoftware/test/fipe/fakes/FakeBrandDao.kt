package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.database.dao.BrandDao
import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBrandDao : BrandDao {

    private val vehiclesBrandsList: MutableList<BrandDto> = mutableListOf()
    private val brandModelsList: MutableList<BrandModelDto> = mutableListOf()

    fun clearData() {
        vehiclesBrandsList.clear()
        brandModelsList.clear()
    }

    override suspend fun insertAllBrands(vehicleBrands: List<BrandDto>) {
        vehiclesBrandsList.addAll(vehicleBrands)
    }

    override suspend fun insertAllBrandModels(brandModels: List<BrandModelDto>) {
        brandModelsList.addAll(brandModels)
    }

    override fun getVehiclesBrands(): Flow<List<BrandDto>> {
        return flow { emit(vehiclesBrandsList) }
    }

    override fun getFirstBrandOnDatabase(): Flow<BrandDto?> {
        TODO("Not yet implemented")
    }

    override fun getBrandModels(): Flow<List<BrandModelDto>> {
        return flow { emit(brandModelsList) }
    }

    override fun getBrandModels(brandCode: String): Flow<List<BrandModelDto>> {
        TODO("Not yet implemented")
    }

    override fun getBrandNameByBrandCode(brandCode: String): Flow<String> {
        TODO("Not yet implemented")
    }
}
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

    override fun insertAllBrands(vehicleBrands: List<BrandDto>) {
        vehiclesBrandsList.addAll(vehicleBrands)
    }

    override fun insertAllBrandModels(brandModels: List<BrandModelDto>) {
        brandModelsList.addAll(brandModels)
    }

    override fun getVehiclesBrands(): Flow<List<BrandDto>> {
        return flow { emit(vehiclesBrandsList) }
    }

    override fun getBrandModels(): Flow<List<BrandModelDto>> {
        return flow { emit(brandModelsList) }
    }
}
package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import com.cajusoftware.fipe.data.network.model.ModelYearResponseDto
import com.cajusoftware.fipe.data.network.model.VehicleResponseDto
import com.cajusoftware.fipe.data.network.services.VehicleApiService

class FakeVehicleApiService : VehicleApiService {
    override suspend fun getBrandModels(brandNumber: String): List<BrandModelResponseDto> {
        return FakeDataSource.brandModelsDtoList
    }

    override suspend fun getBrands(): List<BrandResponseDto> {
        return FakeDataSource.brandResponseDtoLists
    }

    override suspend fun getCar(
        brandNumber: String,
        modelNumber: String,
        modelYear: String
    ): VehicleResponseDto {
        return FakeDataSource.vehicleResponseList.first {
            it.vehicleBrand == brandNumber && it.brandModel == modelNumber && it.modelFuel == modelYear
        }
    }

    override suspend fun getYears(
        brandNumber: String,
        modelNumber: String
    ): List<ModelYearResponseDto> {
        return FakeDataSource.modelYearsResponseList
    }
}
package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import com.cajusoftware.fipe.data.network.services.VehicleApiService

class FakeVehicleApiService : VehicleApiService {
    override suspend fun getBrandModels(brandNumber: String): List<BrandModelResponseDto> {
        return FakeDataSource.brandModelsDtoList
    }

    override suspend fun getBrands(): List<BrandResponseDto> {
        return FakeDataSource.brandResponseDtoLists
    }
}
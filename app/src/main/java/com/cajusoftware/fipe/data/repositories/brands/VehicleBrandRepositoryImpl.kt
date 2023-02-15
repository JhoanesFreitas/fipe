package com.cajusoftware.fipe.data.repositories.brands

import com.cajusoftware.fipe.data.database.dao.BrandDao
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandModel
import com.cajusoftware.fipe.data.network.services.VehicleApiService
import com.cajusoftware.fipe.utils.exts.asBrand
import com.cajusoftware.fipe.utils.exts.asBrandModel
import com.cajusoftware.fipe.utils.exts.asBrandModelDto
import com.cajusoftware.fipe.utils.exts.asVehicleBrandDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class VehicleBrandRepositoryImpl(
    private val brandDao: BrandDao,
    private val vehicleApiService: VehicleApiService
) : VehicleBranchRepository {

    override val vehicleBrands: Flow<List<Brand>> =
        brandDao.getVehiclesBrands().map { it.asBrand() }

    override val brandModels: Flow<List<BrandModel>> =
        brandDao.getBrandModels().map { it.asBrandModel() }

    override suspend fun getAllVehicleBrands() {
        withContext(Dispatchers.IO) {
            val brands = vehicleApiService.getBrands()
            brandDao.insertAllBrands(brands.asVehicleBrandDto())
        }
    }

    override suspend fun getAllBrandModels(brandNumber: String) {
        withContext(Dispatchers.IO) {
            val brands = vehicleApiService.getBrandModels(brandNumber)
            brandDao.insertAllBrandModels(brands.models.asBrandModelDto(brandNumber))
        }
    }

    override fun getBrandName(brandNumber: String): Flow<String> {
        return brandDao.getBrandNameByBrandCode(brandNumber)
    }
}
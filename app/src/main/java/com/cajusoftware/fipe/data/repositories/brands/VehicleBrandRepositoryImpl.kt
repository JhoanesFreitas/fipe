package com.cajusoftware.fipe.data.repositories.brands

import com.cajusoftware.fipe.data.database.dao.BrandDao
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandsModel
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

    override val brandsModels: Flow<List<BrandsModel>> =
        brandDao.getBrandModels().map { it.asBrandModel() }

    override val firstVehicleBrands: Flow<Brand?>
        get() = brandDao.getFirstBrandOnDatabase().map { it?.asBrand() }

    override suspend fun getAllVehicleBrands() {
        withContext(Dispatchers.IO) {
            val brands = vehicleApiService.getBrands()
            brandDao.insertAllBrands(brands.asVehicleBrandDto())
        }
    }

    override suspend fun fetchBrandsModels(brandNumber: String) {
        withContext(Dispatchers.IO) {
            val brands = vehicleApiService.getBrandModels(brandNumber)
            brandDao.insertAllBrandModels(brands.asBrandModelDto(brandNumber))
        }
    }

    override fun getBrandName(brandNumber: String): Flow<String> {
        return brandDao.getBrandNameByBrandCode(brandNumber)
    }

    override fun getBrandsModels(brandNumber: String): Flow<List<BrandsModel>> =
        brandDao.getBrandModels(brandNumber).map { it.asBrandModel() }

}
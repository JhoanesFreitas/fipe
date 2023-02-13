package com.cajusoftware.fipe.data.repositories.models

import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.domain.ModelYear
import com.cajusoftware.fipe.data.domain.Vehicle
import com.cajusoftware.fipe.data.network.services.VehicleApiService
import com.cajusoftware.fipe.utils.exts.asModelYear
import com.cajusoftware.fipe.utils.exts.asModelYearDto
import com.cajusoftware.fipe.utils.exts.asVehicle
import com.cajusoftware.fipe.utils.exts.asVehicleDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehicleRepositoryImpl(
    private val vehicleDao: VehicleDao,
    private val vehicleApiService: VehicleApiService
) : VehicleRepository {

    override val vehicles: Flow<List<Vehicle>>
        get() = vehicleDao.getVehicles().map { it.asVehicle() }

    override suspend fun getVehicle(brandNumber: String, modelNumber: String, modelYear: String) {
        val vehicle = vehicleApiService.getCar(brandNumber, modelNumber, modelYear)
        vehicleDao.insertVehicle(vehicle.asVehicleDto())
    }

    override suspend fun getAllModelYears(brandNumber: String, modelNumber: String) {
        val years = vehicleApiService.getYears(brandNumber, modelNumber)
        vehicleDao.insertModelYears(years.asModelYearDto(brandNumber, modelNumber))
    }

    override fun getVehicleByFipeCode(fipeCode: String): Flow<Vehicle> {
        return vehicleDao.getVehicle(fipeCode).map { it.asVehicle() }
    }

    override fun getModelYears(brandNumber: String, modelNumber: String): Flow<List<ModelYear>> {
        return vehicleDao.getYears(brandNumber, modelNumber).map { it.asModelYear() }
    }
}
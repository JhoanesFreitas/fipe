package com.cajusoftware.fipe.data.repositories.models

import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.domain.Historic
import com.cajusoftware.fipe.data.domain.ModelYear
import com.cajusoftware.fipe.data.domain.Vehicle
import com.cajusoftware.fipe.data.network.services.VehicleApiService
import com.cajusoftware.fipe.utils.exts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class VehicleRepositoryImpl(
    private val vehicleDao: VehicleDao,
    private val vehicleApiService: VehicleApiService
) : VehicleRepository {

    override val vehicles: Flow<List<Vehicle>>
        get() = vehicleDao.getVehicles().map { it.asVehicle() }

    private val _historic: MutableSharedFlow<Historic> = MutableSharedFlow()
    override val historic: Flow<Historic>
        get() = _historic

    override suspend fun fetchVehicle(brandNumber: String, modelNumber: String, modelYear: String) {
        val vehicle = vehicleApiService.getCar(brandNumber, modelNumber, modelYear)
        vehicleDao.insertVehicle(vehicle.asVehicleDto())
    }

    override fun getVehicle(
        brandName: String,
        modelName: String,
        modelYear: String
    ): Flow<Vehicle?> {
        return vehicleDao.getVehicle(brandName, modelName, modelYear)
            .map { it.asVehicle() }
    }

    override suspend fun fetchAllModelYears(brandNumber: String, modelNumber: String) {
        val years = vehicleApiService.getYears(brandNumber, modelNumber)
        vehicleDao.insertModelYears(years.asModelYearDto(brandNumber, modelNumber))
    }

    override fun getVehicleByFipeCode(fipeCode: String): Flow<Vehicle> {
        return vehicleDao.getVehicleByFipeCode(fipeCode).map { it.asVehicle() }
    }

    override suspend fun fetchModelYears(brandNumber: String, modelNumber: String) {
        withContext(Dispatchers.IO) {
            val years = vehicleApiService.getYears(brandNumber, modelNumber)
            vehicleDao.insertModelYears(years.map { it.asModelYearDto(brandNumber, modelNumber) })
        }
    }

    override fun getVehicleYears(brandNumber: String, modelNumber: String): Flow<List<ModelYear>> {
        return vehicleDao.getYears(brandNumber, modelNumber).map { it.asModelYear() }
    }

    override suspend fun fetchPricesByYears(fipeCode: String, year: String) {
        withContext(Dispatchers.IO) {
            val prices = vehicleApiService.getHistoric(fipeCode, year)
            vehicleDao.insertHistoric(prices.asHistoricDto())
        }

        vehicleDao.getHistoric(fipeCode).collect {
            _historic.emit(it.asHistoric())
        }
    }
}
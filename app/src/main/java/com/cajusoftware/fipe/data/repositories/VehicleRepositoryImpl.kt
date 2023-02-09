package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.database.domain.Vehicle
import com.cajusoftware.fipe.utils.exts.asVehicleData
import com.cajusoftware.fipe.utils.exts.asVehicleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehicleRepositoryImpl(private val vehicleDao: VehicleDao) : VehicleRepository {

    override fun getAllVehicles(): Flow<List<Vehicle>> =
        vehicleDao.getVehicles().map { it.asVehicleModel() }

    override fun getVehicle(fipeCode: String): Flow<Vehicle?> =
        vehicleDao.getVehicle(fipeCode).map { it.asVehicleModel() }

    override suspend fun insertItem(vehicle: Vehicle) =
        vehicleDao.insert(vehicle.asVehicleData())

    override suspend fun deleteItem(vehicle: Vehicle) =
        vehicleDao.delete(vehicle.asVehicleData())

    override suspend fun updateItem(vehicle: Vehicle) =
        vehicleDao.update(vehicle.asVehicleData())
}
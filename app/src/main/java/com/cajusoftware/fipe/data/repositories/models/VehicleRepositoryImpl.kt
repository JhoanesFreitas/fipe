package com.cajusoftware.fipe.data.repositories.models

import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.network.model.VehicleResponseDto
import com.cajusoftware.fipe.data.network.services.VehicleApiService
import com.cajusoftware.fipe.utils.exts.asVehicleDto
import com.cajusoftware.fipe.utils.exts.asVehicleResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehicleRepositoryImpl(
    private val vehicleDao: VehicleDao,
    private val vehicleApiService: VehicleApiService
) : VehicleRepository {

    override fun getAllVehicles(): Flow<List<VehicleResponseDto>> =
        vehicleDao.getVehicles().map { it.asVehicleResponseDto() }

    override fun getVehicle(fipeCode: String): Flow<VehicleResponseDto?> =
        vehicleDao.getVehicle(fipeCode).map { it.asVehicleResponseDto() }

    override suspend fun insertVehicle(vehicleResponseDto: VehicleResponseDto) =
        vehicleDao.insert(vehicleResponseDto.asVehicleDto())

    override suspend fun deleteVehicle(vehicleResponseDto: VehicleResponseDto) =
        vehicleDao.delete(vehicleResponseDto.asVehicleDto())

    override suspend fun updateVehicle(vehicleResponseDto: VehicleResponseDto) =
        vehicleDao.update(vehicleResponseDto.asVehicleDto())
}
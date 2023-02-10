package com.cajusoftware.fipe.data.repositories.models

import com.cajusoftware.fipe.data.network.model.VehicleResponseDto
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    fun getAllVehicles(): Flow<List<VehicleResponseDto>>

    fun getVehicle(fipeCode: String): Flow<VehicleResponseDto?>

    suspend fun insertVehicle(vehicleResponseDto: VehicleResponseDto)

    suspend fun deleteVehicle(vehicleResponseDto: VehicleResponseDto)

    suspend fun updateVehicle(vehicleResponseDto: VehicleResponseDto)
}
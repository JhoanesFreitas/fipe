package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.data.database.domain.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    fun getAllVehicles(): Flow<List<Vehicle>>

    fun getVehicle(fipeCode: String): Flow<Vehicle?>

    suspend fun insertVehicle(vehicle: Vehicle)

    suspend fun deleteVehicle(vehicle: Vehicle)

    suspend fun updateVehicle(vehicle: Vehicle)
}
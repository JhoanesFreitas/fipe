package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.data.database.domain.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    fun getAllVehicles(): Flow<List<Vehicle>>

    fun getVehicle(fipeCode: String): Flow<Vehicle?>

    suspend fun insertItem(vehicle: Vehicle)

    suspend fun deleteItem(vehicle: Vehicle)

    suspend fun updateItem(vehicle: Vehicle)
}
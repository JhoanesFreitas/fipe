package com.cajusoftware.fipe.data.repositories.models

import com.cajusoftware.fipe.data.domain.ModelYear
import com.cajusoftware.fipe.data.domain.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    val vehicles: Flow<List<Vehicle>>

    suspend fun getVehicle(brandNumber: String, modelNumber: String, modelYear: String)

    fun getVehicleByFipeCode(fipeCode: String): Flow<Vehicle>

    fun getModelYears(brandNumber: String, modelNumber: String): Flow<List<ModelYear>>

    suspend fun getAllModelYears(brandNumber: String, modelNumber: String)
}
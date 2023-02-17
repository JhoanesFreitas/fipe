package com.cajusoftware.fipe.data.repositories.models

import com.cajusoftware.fipe.data.domain.ModelYear
import com.cajusoftware.fipe.data.domain.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    val vehicles: Flow<List<Vehicle>>

    suspend fun fetchVehicle(brandNumber: String, modelNumber: String, modelYear: String)
    fun getVehicle(brandNumber: String, modelNumber: String, modelYear: String): Flow<Vehicle?>

    fun getVehicleByFipeCode(fipeCode: String): Flow<Vehicle>

    fun getVehicleYears(brandNumber: String, modelNumber: String): Flow<List<ModelYear>>

    suspend fun fetchModelYears(brandNumber: String, modelNumber: String)

    suspend fun fetchAllModelYears(brandNumber: String, modelNumber: String)
}
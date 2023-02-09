package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.database.entities.VehicleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeVehicleDao : VehicleDao {

    private val vehiclesList: MutableList<VehicleData> = mutableListOf()

    override suspend fun insert(vehicleData: VehicleData) {
        vehiclesList.add(vehicleData)
    }

    override suspend fun update(vehicleData: VehicleData) {
        vehiclesList.first { vehicleData.fipeCode == it.fipeCode }.apply {
            vehiclesList[vehiclesList.indexOf(this)] = vehicleData
        }
    }

    override suspend fun delete(vehicleData: VehicleData) {
        vehiclesList.remove(vehicleData)
    }

    override fun getVehicle(fipeCode: String): Flow<VehicleData> {
        val vehicle = vehiclesList.firstOrNull { fipeCode == it.fipeCode }
        return flow { if (vehicle != null) emit(vehicle) }
    }

    override fun getVehicles(): Flow<List<VehicleData>> {
        return flow { emit(vehiclesList) }
    }
}
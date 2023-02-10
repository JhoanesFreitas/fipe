package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.database.dtos.ModelYearDto
import com.cajusoftware.fipe.data.database.dtos.VehicleDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeVehicleDao : VehicleDao {

    private val vehiclesList: MutableList<VehicleDto> = mutableListOf()
    private val modelYearsList: MutableList<ModelYearDto> = mutableListOf()

    override suspend fun insertVehicle(vehicleData: VehicleDto) {
        vehiclesList.add(vehicleData)
    }

    override suspend fun insertModelYears(modelYears: List<ModelYearDto>) {
        modelYearsList.addAll(modelYears)
    }

    override suspend fun update(vehicleData: VehicleDto) {
        vehiclesList.first { vehicleData.fipeCode == it.fipeCode }.apply {
            vehiclesList[vehiclesList.indexOf(this)] = vehicleData
        }
    }

    override suspend fun delete(vehicleData: VehicleDto) {
        vehiclesList.remove(vehicleData)
    }

    override fun getVehicle(fipeCode: String): Flow<VehicleDto> {
        val vehicle = vehiclesList.firstOrNull { fipeCode == it.fipeCode }
        return flow { if (vehicle != null) emit(vehicle) }
    }

    override fun getVehicles(): Flow<List<VehicleDto>> {
        return flow { emit(vehiclesList) }
    }

    override fun getYears(brandNumber: String, modelNumber: String): Flow<List<ModelYearDto>> {
        return flow { emit(modelYearsList.filter { it.brandNumber == brandNumber && it.modelNumber == modelNumber }) }
    }
}
package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.utils.exts.asVehicleData
import com.cajusoftware.test.fipe.fakes.FakeDataSource.vehicleList
import com.cajusoftware.test.fipe.fakes.FakeVehicleDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VehicleRepositoryTest {

    private val fakeVehicleDao = FakeVehicleDao()
    private val repository = VehicleRepositoryImpl(fakeVehicleDao)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun addVehicles() = runTest {
        fakeVehicleDao.apply {
            insert(vehicleList.first().asVehicleData())
            insert(vehicleList.last().asVehicleData())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getAllVehicles_verifyVehicleList() = runTest {
        assertEquals(vehicleList, repository.getAllVehicles().first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getVehicle_verifyVehicle() = runTest {
        assertEquals(
            vehicleList.first(),
            repository.getVehicle(vehicleList.first().fipeCode).first()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_insertVehicle_verifyVehicleIsOnDatabase() = runTest {
        val newVehicle = vehicleList.first().copy(fipeCode = "13542")
        repository.insertVehicle(newVehicle)
        assertEquals(
            newVehicle,
            repository.getVehicle(newVehicle.fipeCode).first()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_updateVehicle_verifyVehicleWasChangedOnDatabase() = runTest {
        val newVehicle = vehicleList.first().copy(fipeCode = "15432")
        repository.insertVehicle(newVehicle)
        val updatedVehicle = newVehicle.copy(modelYear = 2022)
        repository.updateVehicle(updatedVehicle)
        assertEquals(
            updatedVehicle,
            repository.getVehicle(newVehicle.fipeCode).first()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_deleteVehicle_verifyVehicleIsOnDatabase() = runTest {
        val newVehicle = vehicleList.first().copy(fipeCode = "54132")
        repository.insertVehicle(newVehicle)
        repository.deleteVehicle(newVehicle)
        assertNull(repository.getVehicle(newVehicle.fipeCode).firstOrNull())
    }
}
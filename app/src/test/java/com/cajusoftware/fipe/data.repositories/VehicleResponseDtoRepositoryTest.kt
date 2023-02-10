package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.data.repositories.models.VehicleRepositoryImpl
import com.cajusoftware.fipe.utils.exts.asVehicleDto
import com.cajusoftware.test.fipe.fakes.FakeDataSource.vehicleResponseDtoLists
import com.cajusoftware.test.fipe.fakes.FakeVehicleApiService
import com.cajusoftware.test.fipe.fakes.FakeVehicleDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VehicleResponseDtoRepositoryTest {

    private val fakeVehicleDao = FakeVehicleDao()
    private val fakeVehicleApiService = FakeVehicleApiService()
    private val repository = VehicleRepositoryImpl(fakeVehicleDao, fakeVehicleApiService)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun addVehicles() = runTest {
        fakeVehicleDao.apply {
            insert(vehicleResponseDtoLists.first().asVehicleDto())
            insert(vehicleResponseDtoLists.last().asVehicleDto())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getAllVehicles_verifyVehicleList() = runTest {
        assertEquals(vehicleResponseDtoLists, repository.getAllVehicles().first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getVehicle_verifyVehicle() = runTest {
        assertEquals(
            vehicleResponseDtoLists.first(),
            repository.getVehicle(vehicleResponseDtoLists.first().fipeCode).first()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_insertVehicle_verifyVehicleIsOnDatabase() = runTest {
        val newVehicle = vehicleResponseDtoLists.first().copy(fipeCode = "13542")
        repository.insertVehicle(newVehicle)
        assertEquals(
            newVehicle,
            repository.getVehicle(newVehicle.fipeCode).first()
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_updateVehicle_verifyVehicleWasChangedOnDatabase() = runTest {
        val newVehicle = vehicleResponseDtoLists.first().copy(fipeCode = "15432")
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
        val newVehicle = vehicleResponseDtoLists.first().copy(fipeCode = "54132")
        repository.insertVehicle(newVehicle)
        repository.deleteVehicle(newVehicle)
        assertNull(repository.getVehicle(newVehicle.fipeCode).firstOrNull())
    }
}
package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.data.repositories.models.VehicleRepositoryImpl
import com.cajusoftware.fipe.utils.exts.asVehicleDto
import com.cajusoftware.test.fipe.fakes.FakeDataSource.vehicleList
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

class VehicleRepositoryTest {

    private val fakeVehicleDao = FakeVehicleDao()
    private val fakeVehicleApiService = FakeVehicleApiService()
    private val repository = VehicleRepositoryImpl(fakeVehicleDao, fakeVehicleApiService)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun addVehicles() = runTest {
        fakeVehicleDao.apply {
            insertVehicle(vehicleResponseDtoLists.first().asVehicleDto())
            insertVehicle(vehicleResponseDtoLists.last().asVehicleDto())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getAllVehicles_verifyVehicleList() = runTest {
        assertEquals(vehicleList, repository.vehicles.first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getVehicle_verifyVehicle() = runTest {
        assertEquals(
            vehicleList.first(),
            repository.getVehicleByFipeCode(vehicleList.first().fipeCode).first()
        )
    }
}
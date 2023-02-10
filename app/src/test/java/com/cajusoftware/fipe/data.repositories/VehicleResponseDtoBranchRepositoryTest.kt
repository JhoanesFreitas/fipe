package com.cajusoftware.fipe.data.repositories

import com.cajusoftware.fipe.data.repositories.brands.VehicleBrandRepositoryImpl
import com.cajusoftware.test.fipe.fakes.FakeBrandDao
import com.cajusoftware.test.fipe.fakes.FakeDataSource
import com.cajusoftware.test.fipe.fakes.FakeVehicleApiService
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

class VehicleResponseDtoBranchRepositoryTest {
    private val fakeVehicleBrandDao = FakeBrandDao()
    private val fakeVehicleApiService = FakeVehicleApiService()
    private val repository = VehicleBrandRepositoryImpl(fakeVehicleBrandDao, fakeVehicleApiService)

    @After
    fun clear() {
        fakeVehicleBrandDao.clearData()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getAllVehicleBrands_verifyVehicleBrandsList() = runTest {
        repository.getAllVehicleBrands()
        repository.vehicleBrands.collect {
            TestCase.assertEquals(FakeDataSource.brandsList, it)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun vehicleRepository_getAllBrandModels_verifyBrandModelsList() = runTest {
        repository.getAllBrandModels(FakeDataSource.brandsList.first().code)
        repository.brandModels.collect {
            TestCase.assertEquals(FakeDataSource.brandModelsList, it)
        }
    }
}
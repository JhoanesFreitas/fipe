package com.cajusoftware.fipe.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cajusoftware.fipe.data.database.FipeDatabase
import com.cajusoftware.fipe.utils.exts.asBrandDto
import com.cajusoftware.fipe.utils.exts.asBrandModelDto
import com.cajusoftware.test.fipe.fakes.FakeDataSource
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BrandDaoTest {
    private lateinit var brandDao: BrandDao
    private lateinit var fipeDatabase: FipeDatabase

    @Test
    @Throws(IOException::class)
    fun daoInsertAllBrands_insertVehicleBrandsIntoDatabase() = runBlocking {
        addAllBrandsOnDatabase()
        val allVehicles = brandDao.getVehiclesBrands().first()
        TestCase.assertEquals(
            FakeDataSource.brandsList.first().asBrandDto(),
            allVehicles.first()
        )
    }

    @Test
    @Throws(IOException::class)
    fun daoGetVehiclesBrands_returnAllVehiclesBrandsFromDatabase() = runBlocking {
        addAllBrandsOnDatabase()
        val allVehicles = brandDao.getVehiclesBrands().first()
        TestCase.assertEquals(
            FakeDataSource.brandsList.first().asBrandDto(),
            allVehicles.first()
        )
        TestCase.assertEquals(
            FakeDataSource.brandsList.last().asBrandDto(),
            allVehicles.last()
        )
    }

    @Test
    @Throws(IOException::class)
    fun daoInsertAllBrandModels_insertBrandModelsIntoDatabase() = runBlocking {
        addAllBrandModelsOnDatabase()
        val allModels = brandDao.getBrandModels().first()
        TestCase.assertEquals(
            FakeDataSource.brandsModelsLists.first().asBrandModelDto(),
            allModels.first()
        )
    }

    @Test
    @Throws(IOException::class)
    fun daoGetBrandModels_returnAllVehiclesBrandsFromDatabase() = runBlocking {
        addAllBrandModelsOnDatabase()
        val allModels = brandDao.getBrandModels().first()
        TestCase.assertEquals(
            FakeDataSource.brandsModelsLists.first().asBrandModelDto(),
            allModels.first()
        )
        TestCase.assertEquals(
            FakeDataSource.brandsModelsLists.last().asBrandModelDto(),
            allModels.last()
        )
    }

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        fipeDatabase = Room.inMemoryDatabaseBuilder(context, FipeDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        brandDao = fipeDatabase.vehicleBrandDao()
    }

    private suspend fun addAllBrandsOnDatabase() {
        brandDao.insertAllBrands(FakeDataSource.brandsList.asBrandDto())
    }

    private suspend fun addAllBrandModelsOnDatabase() {
        brandDao.insertAllBrandModels(FakeDataSource.brandsModelsLists.asBrandModelDto())
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        fipeDatabase.close()
    }
}
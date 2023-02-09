package com.cajusoftware.fipe.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cajusoftware.fipe.data.database.FipeDatabase
import com.cajusoftware.fipe.data.fakes.FakeDataSource.vehicleList
import com.cajusoftware.fipe.utils.exts.asVehicleData
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VehicleDaoTest {

    private lateinit var vehicleDao: VehicleDao
    private lateinit var fipeDatabase: FipeDatabase

    @Test
    @Throws(IOException::class)
    fun daoInset_insertVehicleIntoDatabase() = runBlocking {
        addOneItemToDb()
        val allVehicles = vehicleDao.getVehicles().first()
        assertEquals(vehicleList.first().asVehicleData(), allVehicles.first())
    }

    @Test
    @Throws(IOException::class)
    fun daoGetAllVehicles_returnAllVehiclesFromDatabase() = runBlocking {
        addTwoItemsToDb()
        val allVehicles = vehicleDao.getVehicles().first()
        assertEquals(vehicleList.first().asVehicleData(), allVehicles.first())
        assertEquals(vehicleList.last().asVehicleData(), allVehicles.last())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateVehicles_updatesVehiclesInDatabase() = runBlocking {
        addTwoItemsToDb()
        val updatedVehicleOne = vehicleList.first().copy(modelYear = 2017).asVehicleData()
        val updatedVehicleTwo =
            vehicleList.last().copy(vehiclePrice = "R$ 71.000,00").asVehicleData()
        vehicleDao.update(updatedVehicleOne)
        vehicleDao.update(updatedVehicleTwo)

        val vehicles = vehicleDao.getVehicles().first()
        assertEquals(updatedVehicleOne, vehicles.first())
        assertEquals(updatedVehicleTwo, vehicles.last())
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteVehicles_deletesAllItemsFromDatabase() = runBlocking {
        addTwoItemsToDb()
        vehicleDao.delete(vehicleList.first().asVehicleData())
        vehicleDao.delete(vehicleList.last().asVehicleData())

        val allItems = vehicleDao.getVehicles().first()
        TestCase.assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetVehicle_returnsVehicleFromDatabase() = runBlocking {
        addOneItemToDb()

        val vehicle = vehicleDao.getVehicle(vehicleList.first().fipeCode)
        assertEquals(vehicleList.first().asVehicleData(), vehicle.first())
    }

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        fipeDatabase = Room.inMemoryDatabaseBuilder(context, FipeDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        vehicleDao = fipeDatabase.vehicleDao()
    }

    private suspend fun addOneItemToDb() {
        vehicleDao.insert(vehicleList.first().asVehicleData())
    }

    private suspend fun addTwoItemsToDb() {
        vehicleDao.insert(vehicleList.first().asVehicleData())
        vehicleDao.insert(vehicleList.last().asVehicleData())
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        fipeDatabase.close()
    }
}
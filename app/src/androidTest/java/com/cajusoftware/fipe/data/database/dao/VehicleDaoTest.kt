package com.cajusoftware.fipe.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cajusoftware.fipe.data.database.FipeDatabase
import com.cajusoftware.fipe.utils.exts.asVehicleDto
import com.cajusoftware.test.fipe.fakes.FakeDataSource.vehicleResponseDtoLists
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
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
        assertEquals(vehicleResponseDtoLists.first().asVehicleDto(), allVehicles.first())
    }

    @Test
    @Throws(IOException::class)
    fun daoGetAllVehicles_returnAllVehiclesFromDatabase() = runBlocking {
        addTwoItemsToDb()
        val allVehicles = vehicleDao.getVehicles().first()
        assertEquals(vehicleResponseDtoLists.first().asVehicleDto(), allVehicles.first())
        assertEquals(vehicleResponseDtoLists.last().asVehicleDto(), allVehicles.last())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateVehicles_updatesVehiclesInDatabase() = runBlocking {
        addTwoItemsToDb()
        val updatedVehicleOne = vehicleResponseDtoLists.first().copy(modelYear = 2017).asVehicleDto()
        val updatedVehicleTwo =
            vehicleResponseDtoLists.last().copy(vehiclePrice = "R$ 71.000,00").asVehicleDto()
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
        vehicleDao.delete(vehicleResponseDtoLists.first().asVehicleDto())
        vehicleDao.delete(vehicleResponseDtoLists.last().asVehicleDto())

        val allItems = vehicleDao.getVehicles().first()
        TestCase.assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetVehicle_returnsVehicleFromDatabase() = runBlocking {
        addOneItemToDb()

        val vehicle = vehicleDao.getVehicle(vehicleResponseDtoLists.first().fipeCode)
        assertEquals(vehicleResponseDtoLists.first().asVehicleDto(), vehicle.first())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetVehicle_returnsNullIfHasNonExistentVehicleFromDatabase() = runBlocking {
        addOneItemToDb()

        val vehicle = vehicleDao.getVehicle("98980776")
        assertNull(vehicle.first())
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
        vehicleDao.insert(vehicleResponseDtoLists.first().asVehicleDto())
    }

    private suspend fun addTwoItemsToDb() {
        vehicleDao.insert(vehicleResponseDtoLists.first().asVehicleDto())
        vehicleDao.insert(vehicleResponseDtoLists.last().asVehicleDto())
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        fipeDatabase.close()
    }
}
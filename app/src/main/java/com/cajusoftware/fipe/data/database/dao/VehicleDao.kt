package com.cajusoftware.fipe.data.database.dao

import androidx.room.*
import com.cajusoftware.fipe.data.database.entities.VehicleData
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehicleData: VehicleData)

    @Update
    suspend fun update(vehicleData: VehicleData)

    @Delete
    suspend fun delete(vehicleData: VehicleData)

    @Query("SELECT * from vehicle WHERE fipe_code = :fipeCode")
    fun getVehicle(fipeCode: String): Flow<VehicleData>

    @Query("SELECT * from vehicle ORDER BY brand ASC")
    fun getVehicles(): Flow<List<VehicleData>>
}
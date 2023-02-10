package com.cajusoftware.fipe.data.database.dao

import androidx.room.*
import com.cajusoftware.fipe.data.database.dtos.VehicleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehicleData: VehicleDto)

    @Update
    suspend fun update(vehicleData: VehicleDto)

    @Delete
    suspend fun delete(vehicleData: VehicleDto)

    @Query("SELECT * from vehicle WHERE fipe_code = :fipeCode")
    fun getVehicle(fipeCode: String): Flow<VehicleDto>

    @Query("SELECT * from vehicle ORDER BY brand ASC")
    fun getVehicles(): Flow<List<VehicleDto>>
}
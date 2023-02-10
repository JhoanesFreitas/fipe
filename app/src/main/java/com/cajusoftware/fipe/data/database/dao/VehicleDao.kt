package com.cajusoftware.fipe.data.database.dao

import androidx.room.*
import com.cajusoftware.fipe.data.database.dtos.ModelYearDto
import com.cajusoftware.fipe.data.database.dtos.VehicleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVehicle(vehicleData: VehicleDto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertModelYears(modelYears: List<ModelYearDto>)

    @Update
    suspend fun update(vehicleData: VehicleDto)

    @Delete
    suspend fun delete(vehicleData: VehicleDto)

    @Query("SELECT * from vehicle WHERE fipe_code = :fipeCode")
    fun getVehicle(fipeCode: String): Flow<VehicleDto>

    @Query("SELECT * from vehicle ORDER BY brand ASC")
    fun getVehicles(): Flow<List<VehicleDto>>

    @Query("SELECT * from years WHERE brand = :brandNumber AND model = :modelNumber ORDER BY code DESC")
    fun getYears(brandNumber: String, modelNumber: String): Flow<List<ModelYearDto>>
}
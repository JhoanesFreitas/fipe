package com.cajusoftware.fipe.data.database.dao

import androidx.room.*
import com.cajusoftware.fipe.data.database.dtos.HistoricDto
import com.cajusoftware.fipe.data.database.dtos.ModelYearDto
import com.cajusoftware.fipe.data.database.dtos.VehicleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVehicle(vehicleData: VehicleDto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertModelYears(modelYears: List<ModelYearDto>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistoric(modelYears: HistoricDto)

    @Update
    suspend fun update(vehicleData: VehicleDto)

    @Delete
    suspend fun delete(vehicleData: VehicleDto)

    @Query("SELECT * from vehicle WHERE fipe_code = :fipeCode")
    fun getVehicleByFipeCode(fipeCode: String): Flow<VehicleDto>

    @Query("SELECT * from vehicle WHERE brand = :brandNumber AND model = :modelNumber AND year = :modelYear")
    fun getVehicle(brandNumber: String, modelNumber: String, modelYear: String): Flow<VehicleDto>

    @Query("SELECT * from vehicle ORDER BY brand ASC")
    fun getVehicles(): Flow<List<VehicleDto>>

    @Query("SELECT * from years WHERE brand = :brandNumber AND model = :modelNumber ORDER BY code DESC")
    fun getYears(brandNumber: String, modelNumber: String): Flow<List<ModelYearDto>>

    @Query("SELECT * from historic WHERE fipe_code = :fipeCode")
    fun getHistoric(fipeCode: String): Flow<HistoricDto>
}
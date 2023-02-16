package com.cajusoftware.fipe.data.database.dao

import androidx.room.*
import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBrands(vehicleBrands: List<BrandDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBrandModels(brandModels: List<BrandModelDto>)

    @Query("SELECT * from vehicle_brand")
    fun getVehiclesBrands(): Flow<List<BrandDto>>

    @Query("SELECT * from vehicle_brand LIMIT 1")
    fun getFirstBrandOnDatabase(): Flow<BrandDto?>

    @Query("SELECT * from brand_model")
    fun getBrandModels(): Flow<List<BrandModelDto>>

    @Query("SELECT * from brand_model WHERE brand = :brandCode")
    fun getBrandModels(brandCode: String): Flow<List<BrandModelDto>>

    @Query("SELECT name from vehicle_brand WHERE code = :brandCode")
    fun getBrandNameByBrandCode(brandCode: String): Flow<String>
}
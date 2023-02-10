package com.cajusoftware.fipe.data.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle")
data class VehicleDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "fipe_code")
    val fipeCode: String,
    val value: String,
    val brand: String,
    @ColumnInfo(name = "model")
    val brandModel: String,
    @ColumnInfo(name = "year")
    val modelYear: Long,
    @ColumnInfo(name = "fuel")
    val modelFuel: String?,
    @ColumnInfo(name = "reference_month")
    val referenceMonth: String,
    @ColumnInfo(name = "vehicle_type")
    val vehicleType: Int,
    @ColumnInfo(name = "fuel_acronym")
    val fuelAcronym: String
)

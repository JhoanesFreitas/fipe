package com.cajusoftware.fipe.data.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historic")
data class HistoricDto(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "fipe_code") val fipeCode: String,
    @ColumnInfo(name = "brand") val vehicleBrand: String,
    @ColumnInfo(name = "model") val brandModel: String,
    @ColumnInfo(name = "model_year") val modelYear: Long,
    @ColumnInfo(name = "fuel") val modelFuel: String?,
    @ColumnInfo(name = "type") val vehicleType: Int,
    @ColumnInfo(name = "fuel_acronym") val fuelAcronym: String,
    @ColumnInfo(name = "historic") val historic: List<PriceDto>
)

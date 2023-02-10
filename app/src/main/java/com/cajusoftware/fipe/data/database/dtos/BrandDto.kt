package com.cajusoftware.fipe.data.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_brand")
data class BrandDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code")
    val brandCode: String,
    val name: String
)
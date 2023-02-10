package com.cajusoftware.fipe.data.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand_model")
data class BrandModelDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code")
    val code: String,
    val name: String
)

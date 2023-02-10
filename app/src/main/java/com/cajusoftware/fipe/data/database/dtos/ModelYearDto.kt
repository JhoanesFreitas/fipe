package com.cajusoftware.fipe.data.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "years")
data class ModelYearDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code")
    val code: String,
    val name: String,
    @ColumnInfo(name = "brand")
    val brandNumber: String,
    @ColumnInfo(name = "model")
    val modelNumber: String
)
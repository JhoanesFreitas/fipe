package com.cajusoftware.fipe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cajusoftware.fipe.BuildConfig
import com.cajusoftware.fipe.data.database.dao.BrandDao
import com.cajusoftware.fipe.data.database.dao.VehicleDao
import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import com.cajusoftware.fipe.data.database.dtos.VehicleDto

@Database(
    entities = [VehicleDto::class, BrandDto::class, BrandModelDto::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class FipeDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun vehicleBrandDao(): BrandDao

    companion object {
        @Volatile
        private var Instance: FipeDatabase? = null

        fun getDatabase(context: Context): FipeDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FipeDatabase::class.java, "fipe_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
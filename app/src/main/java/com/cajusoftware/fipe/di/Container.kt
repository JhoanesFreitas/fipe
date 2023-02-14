package com.cajusoftware.fipe.di

import android.content.Context
import com.cajusoftware.fipe.BuildConfig
import com.cajusoftware.fipe.BuildConfig.BASE_URL
import com.cajusoftware.fipe.data.database.FipeDatabase
import com.cajusoftware.fipe.data.network.services.VehicleApiService
import com.cajusoftware.fipe.data.repositories.brands.VehicleBranchRepository
import com.cajusoftware.fipe.data.repositories.brands.VehicleBrandRepositoryImpl
import com.cajusoftware.fipe.data.repositories.models.VehicleRepository
import com.cajusoftware.fipe.data.repositories.models.VehicleRepositoryImpl
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface Container {
    val vehicleRepository: VehicleRepository
    val vehicleBrandRepository: VehicleBranchRepository
}

class AppContainer(private val context: Context) : Container {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG)
            addInterceptor(OkHttpProfilerInterceptor())
    }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    private val apiService: VehicleApiService by lazy {
        retrofit.create(VehicleApiService::class.java)
    }

    override val vehicleRepository: VehicleRepository by lazy {
        VehicleRepositoryImpl(FipeDatabase.getDatabase(context).vehicleDao(), apiService)
    }

    override val vehicleBrandRepository: VehicleBranchRepository by lazy {
        VehicleBrandRepositoryImpl(FipeDatabase.getDatabase(context).vehicleBrandDao(), apiService)
    }
}
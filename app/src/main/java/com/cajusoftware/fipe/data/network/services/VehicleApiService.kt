package com.cajusoftware.fipe.data.network.services

import com.cajusoftware.fipe.data.network.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService {

    @GET("cars/brands")
    suspend fun getBrands(): List<BrandResponseDto>

    @GET("cars/brands/{brandNumber}/models")
    suspend fun getBrandModels(@Path("brandNumber") brandNumber: String): List<BrandModelResponseDto>

    @GET("cars/brands/{brandNumber}/models/{modelNumber}/years/{modelYear}")
    suspend fun getCar(
        @Path("brandNumber") brandNumber: String,
        @Path("modelNumber") modelNumber: String,
        @Path("modelYear") modelYear: String,
    ): VehicleResponseDto

    @GET("cars/brands/{brandNumber}/models/{modelNumber}/years")
    suspend fun getYears(
        @Path("brandNumber") brandNumber: String,
        @Path("modelNumber") modelNumber: String
    ): List<ModelYearResponseDto>

    @GET("cars/{fipeCode}/years/{year}/history")
    suspend fun getHistoric(
        @Path("fipeCode") fipeCode: String,
        @Path("year") year: String
    ): HistoricModelResponseDto
}
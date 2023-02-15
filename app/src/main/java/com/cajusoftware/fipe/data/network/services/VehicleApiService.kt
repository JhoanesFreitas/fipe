package com.cajusoftware.fipe.data.network.services

import com.cajusoftware.fipe.data.network.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService {

    @GET("carros/marcas")
    suspend fun getBrands(): List<BrandResponseDto>

    @GET("carros/marcas/{brandNumber}/modelos")
    suspend fun getBrandModels(@Path("brandNumber") brandNumber: String): BrandModelListDto

    @GET("carros/marcas/{brandNumber}/modelos/{modelNumber}/anos/{modelYear}")
    suspend fun getCar(
        @Path("brandNumber") brandNumber: String,
        @Path("modelNumber") modelNumber: String,
        @Path("modelYear") modelYear: String,
    ): VehicleResponseDto

    @GET("carros/marcas/{brandNumber}/modelos/{modelNumber}/anos")
    suspend fun getYears(
        @Path("brandNumber") brandNumber: String,
        @Path("modelNumber") modelNumber: String
    ): List<ModelYearResponseDto>
}
package com.cajusoftware.fipe.data.network.services

import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import com.cajusoftware.fipe.data.network.model.ModelYearResponseDto
import com.cajusoftware.fipe.data.network.model.VehicleResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService {

    @GET("carros/marcas")
    suspend fun getBrands(): List<BrandResponseDto>

    @GET("carros/marcas/{brandNumber}/modelos")
    suspend fun getBrandModels(@Path("brandNumber") brandNumber: String): List<BrandModelResponseDto>

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
package com.cajusoftware.fipe.data.network.services

import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService {

    @GET("carros/marcas/{brandNumber}/modelos")
    suspend fun getBrandModels(@Path("brandNumber") brandNumber: String): List<BrandModelResponseDto>

    @GET("carros/marcas")
    suspend fun getBrands(): List<BrandResponseDto>
}
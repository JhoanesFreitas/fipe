package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import com.cajusoftware.fipe.data.database.dtos.ModelYearDto
import com.cajusoftware.fipe.data.database.dtos.VehicleDto
import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import com.cajusoftware.fipe.data.network.model.ModelYearResponseDto
import com.cajusoftware.fipe.data.network.model.VehicleResponseDto
import java.text.NumberFormat

fun List<VehicleResponseDto>.asVehicleDto(): List<VehicleDto> =
    map {
        it.asVehicleDto()
    }

fun VehicleResponseDto.asVehicleDto(): VehicleDto =
    VehicleDto(
        fipeCode,
        vehiclePrice,
        vehicleBrand,
        brandModel,
        modelYear,
        modelFuel,
        referenceMonth,
        vehicleType,
        fuelAcronym
    )

fun VehicleResponseDto.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(vehiclePrice)

fun List<BrandResponseDto>.asVehicleBrandDto(): List<BrandDto> =
    map {
        it.asVehicleBrandDto()
    }

fun BrandResponseDto.asVehicleBrandDto(): BrandDto =
    BrandDto(code, name)

fun List<BrandModelResponseDto>.asBrandModelDto(): List<BrandModelDto> =
    map {
        it.asBrandModelDto()
    }

fun BrandModelResponseDto.asBrandModelDto(): BrandModelDto =
    BrandModelDto(code, name)

fun List<ModelYearResponseDto>.asModelYearDto(
    brandNumber: String,
    modelNumber: String
): List<ModelYearDto> =
    map {
        it.asModelYearDto(brandNumber, modelNumber)
    }

fun ModelYearResponseDto.asModelYearDto(brandNumber: String, modelNumber: String): ModelYearDto =
    ModelYearDto(code, name, brandNumber, modelNumber)
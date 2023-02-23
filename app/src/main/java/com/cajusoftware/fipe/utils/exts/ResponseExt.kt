package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.dtos.*
import com.cajusoftware.fipe.data.network.model.*
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
    map { it.asVehicleBrandDto() }

fun BrandResponseDto.asVehicleBrandDto(): BrandDto =
    BrandDto(code, name)

fun List<BrandModelResponseDto>.asBrandModelDto(brandNumber: String): List<BrandModelDto> =
    map { it.asBrandModelDto(brandNumber) }

fun BrandModelResponseDto.asBrandModelDto(brandNumber: String): BrandModelDto =
    BrandModelDto(code, name, brandNumber, null, null)

fun List<ModelYearResponseDto>.asModelYearDto(
    brandNumber: String,
    modelNumber: String
): List<ModelYearDto> =
    map { it.asModelYearDto(brandNumber, modelNumber) }

fun ModelYearResponseDto.asModelYearDto(brandNumber: String, modelNumber: String): ModelYearDto =
    ModelYearDto(code, name, brandNumber, modelNumber)

fun HistoricModelResponseDto.asHistoricDto() =
    HistoricDto(
        fipeCode,
        vehicleBrand,
        brandModel,
        modelYear,
        modelFuel,
        vehicleType,
        fuelAcronym,
        historic.map { it.asPriceDto() }
    )

fun PriceResponseDto.asPriceDto() =
    PriceDto(price, month, reference)
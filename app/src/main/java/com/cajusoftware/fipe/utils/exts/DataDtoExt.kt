package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import com.cajusoftware.fipe.data.database.dtos.ModelYearDto
import com.cajusoftware.fipe.data.database.dtos.VehicleDto
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandsModel
import com.cajusoftware.fipe.data.domain.ModelYear
import com.cajusoftware.fipe.data.domain.Vehicle
import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import com.cajusoftware.fipe.data.network.model.VehicleResponseDto

fun List<VehicleDto>.asVehicleResponseDto(): List<VehicleResponseDto> =
    map {
        it.asVehicleResponseDto()
    }

fun VehicleDto.asVehicleResponseDto(): VehicleResponseDto =
    VehicleResponseDto(
        fipeCode,
        value,
        brand,
        brandModel,
        modelYear,
        modelFuel,
        referenceMonth,
        vehicleType,
        fuelAcronym
    )

fun List<VehicleDto>.asVehicle(): List<Vehicle> =
    map {
        it.asVehicle()
    }

fun VehicleDto.asVehicle(): Vehicle =
    Vehicle(
        fipeCode,
        value,
        brand,
        brandModel,
        modelYear,
        modelFuel,
        referenceMonth,
        vehicleType,
        fuelAcronym
    )

fun List<ModelYearDto>.asModelYear(): List<ModelYear> =
    map {
        it.asModelYear()
    }

fun ModelYearDto.asModelYear(): ModelYear =
    ModelYear(code, name)

fun List<BrandDto>.asBrandResponseDto(): List<BrandResponseDto> =
    map {
        it.asBrandResponseDto()
    }

fun BrandDto.asBrandResponseDto(): BrandResponseDto = BrandResponseDto(brandCode, name)


fun List<BrandModelDto>.asBrandModelDto(): List<BrandModelResponseDto> =
    map {
        it.asBrandModelDto()
    }

fun BrandModelDto.asBrandModelDto(): BrandModelResponseDto =
    BrandModelResponseDto(code, name)

fun List<BrandDto>.asBrand(): List<Brand> =
    map {
        it.asBrand()
    }

fun BrandDto.asBrand(): Brand = Brand(brandCode, name)

fun List<BrandModelDto>.asBrandModel(): List<BrandsModel> =
    map { it.asBrandModel() }

fun BrandModelDto.asBrandModel(): BrandsModel =
    BrandsModel(code, name, brandNumber)
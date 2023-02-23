package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandsModel

fun List<Brand>.asBrandDto(): List<BrandDto> =
    map {
        it.asBrandDto()
    }

fun Brand.asBrandDto(): BrandDto =
    BrandDto(code, name)

fun List<BrandsModel>.asBrandModelDto(brandName: String): List<BrandModelDto> =
    map { it.asBrandModelDto(brandName) }

fun BrandsModel.asBrandModelDto(brandName: String): BrandModelDto =
    BrandModelDto(code, name, brandName, fipeCode, year)
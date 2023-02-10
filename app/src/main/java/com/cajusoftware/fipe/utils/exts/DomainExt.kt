package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.dtos.BrandDto
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandModel

fun List<Brand>.asBrandDto(): List<BrandDto> =
    map {
        it.asBrandDto()
    }

fun Brand.asBrandDto(): BrandDto =
    BrandDto(code, name)

fun List<BrandModel>.asBrandModelDto(): List<BrandModelDto> =
    map {
        it.asBrandModelDto()
    }

fun BrandModel.asBrandModelDto(): BrandModelDto =
    BrandModelDto(code, name)
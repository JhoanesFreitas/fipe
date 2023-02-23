package com.cajusoftware.fipe.data.network.model

import com.squareup.moshi.Json

data class BrandModelListDto(
    @Json(name = "modelos") val models: List<BrandModelResponseDto>,
    @Json(name = "anos") val years: List<BrandModelYearsResponseDto>
)

data class BrandModelResponseDto(
    val code: String,
    val name: String
)

data class BrandModelYearsResponseDto(
    val code: String,
    val name: String
)
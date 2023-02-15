package com.cajusoftware.fipe.data.network.model

import com.squareup.moshi.Json

data class BrandModelListDto(
    @Json(name = "modelos") val models: List<BrandModelResponseDto>,
)

data class BrandModelResponseDto(
    @Json(name = "codigo") val code: String,
    @Json(name = "nome") val name: String
)
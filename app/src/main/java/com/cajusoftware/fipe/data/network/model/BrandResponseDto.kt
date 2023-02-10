package com.cajusoftware.fipe.data.network.model

import com.squareup.moshi.Json

data class BrandResponseDto(
    @Json(name = "codigo") val code: String,
    @Json(name = "nome") val name: String
)

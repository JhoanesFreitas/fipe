package com.cajusoftware.fipe.data.network.model

import com.squareup.moshi.Json

data class HistoricModelResponseDto(
    @Json(name = "codeFipe") val fipeCode: String,
    @Json(name = "brand") val vehicleBrand: String,
    @Json(name = "model") val brandModel: String,
    @Json(name = "modelYear") val modelYear: Long,
    @Json(name = "fuel") val modelFuel: String?,
    @Json(name = "vehicleType") val vehicleType: Int,
    @Json(name = "fuelAcronym") val fuelAcronym: String,
    @Json(name = "priceHistory") val historic: List<PriceResponseDto>
)

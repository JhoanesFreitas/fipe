package com.cajusoftware.fipe.data.network.model

import com.squareup.moshi.Json

data class VehicleResponseDto(
    @Json(name = "CodigoFipe") val fipeCode: String,
    @Json(name = "Valor") val vehiclePrice: String,
    @Json(name = "Marca") val vehicleBrand: String,
    @Json(name = "Modelo") val brandModel: String,
    @Json(name = "AnoModelo") val modelYear: Long,
    @Json(name = "Combustivel") val modelFuel: String?,
    @Json(name = "MesReferencia") val referenceMonth: String,
    @Json(name = "TipoVeiculo") val vehicleType: Int,
    @Json(name = "SiglaCombustivel") val fuelAcronym: String
)

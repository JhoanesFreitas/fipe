package com.cajusoftware.fipe.data.network.model

data class VehicleResponseDto(
    val fipeCode: String,
    val vehiclePrice: String,
    val vehicleBrand: String,
    val brandModel: String,
    val modelYear: Long,
    val modelFuel: String?,
    val referenceMonth: String,
    val vehicleType: Int,
    val fuelAcronym: String
)

package com.cajusoftware.fipe.data.domain

import com.cajusoftware.fipe.utils.exts.toUrlComplement

data class Vehicle(
    val fipeCode: String,
    val vehiclePrice: String,
    val vehicleBrand: String,
    val brandModel: String,
    val modelYear: Long,
    val modelFuel: String?,
    val referenceMonth: String,
    val vehicleType: Int,
    val fuelAcronym: String
) {
    fun yearToString() =
        this.modelYear.toString()

    fun toUrlComplement() =
        this.vehicleBrand.toUrlComplement()
}

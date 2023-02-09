package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.domain.Vehicle
import com.cajusoftware.fipe.data.database.entities.VehicleData
import java.text.NumberFormat

fun List<Vehicle>.asVehicleData(): List<VehicleData> =
    map {
        it.asVehicleData()
    }

fun Vehicle.asVehicleData(): VehicleData =
    VehicleData(
        fipeCode,
        vehiclePrice,
        vehicleBrand,
        brandModel,
        modelYear,
        modelFuel,
        referenceMonth,
        vehicleType,
        fuelAcronym
    )

fun Vehicle.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(vehiclePrice)
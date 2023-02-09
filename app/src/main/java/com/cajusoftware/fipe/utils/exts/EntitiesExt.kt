package com.cajusoftware.fipe.utils.exts

import com.cajusoftware.fipe.data.database.domain.Vehicle
import com.cajusoftware.fipe.data.database.entities.VehicleData

fun List<VehicleData>.asVehicleModel(): List<Vehicle> =
    map {
        it.asVehicleModel()
    }

fun VehicleData.asVehicleModel(): Vehicle =
    Vehicle(
        fipeCode,
        value,
        brand,
        brandModel,
        modelYear,
        modelFuel,
        referenceMonth,
        vehicleType,
        fuelAcronym
    )

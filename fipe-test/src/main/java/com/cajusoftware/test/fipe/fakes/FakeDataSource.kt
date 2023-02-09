package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.database.domain.Vehicle

object FakeDataSource {
    private const val fipeCodeOne: String = "004399-0"
    private const val vehiclePriceOne: String = "R$ 72.312,00"
    private const val vehicleBrandOne: String = "GM - Chevrolet"
    private const val brandModelOne: String = "CRUZE HB Sport LT 1.8 16V FlexP. 5p Aut"
    private const val modelYearOne: Long = 2016
    private const val modelFuelOne: String = "Gasolina"
    private const val referenceMonthOne: String = "fevereiro de 2023 "
    private const val vehicleTypeOne: Int = 1
    private const val fuelAcronymOne: String = "G"

    private const val fipeCodeTwo: String = "004444-0"
    private const val vehiclePriceTwo: String = "R$ 75.728,00"
    private const val vehicleBrandTwo: String = "GM - Chevrolet"
    private const val brandModelTwo: String = "TRACKER LTZ 1.8 16V Flex 4x2 Aut."
    private const val modelYearTwo: Long = 2016
    private const val modelFuelTwo: String = "Gasolina"
    private const val referenceMonthTwo: String = "fevereiro de 2023 "
    private const val vehicleTypeTwo: Int = 1
    private const val fuelAcronymTwo: String = "G"

    val vehicleList = listOf(
        Vehicle(
            fipeCodeOne,
            vehiclePriceOne,
            vehicleBrandOne,
            brandModelOne,
            modelYearOne,
            modelFuelOne,
            referenceMonthOne,
            vehicleTypeOne,
            fuelAcronymOne
        ),
        Vehicle(
            fipeCodeTwo,
            vehiclePriceTwo,
            vehicleBrandTwo,
            brandModelTwo,
            modelYearTwo,
            modelFuelTwo,
            referenceMonthTwo,
            vehicleTypeTwo,
            fuelAcronymTwo
        )
    )
}
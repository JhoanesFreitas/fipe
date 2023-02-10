package com.cajusoftware.test.fipe.fakes

import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.data.domain.BrandModel
import com.cajusoftware.fipe.data.domain.ModelYear
import com.cajusoftware.fipe.data.domain.Vehicle
import com.cajusoftware.fipe.data.network.model.BrandModelResponseDto
import com.cajusoftware.fipe.data.network.model.BrandResponseDto
import com.cajusoftware.fipe.data.network.model.ModelYearResponseDto
import com.cajusoftware.fipe.data.network.model.VehicleResponseDto

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

    val vehicleResponseDtoLists = listOf(
        VehicleResponseDto(
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
        VehicleResponseDto(
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

    private const val chevrolet = "GM - Chevrolet"
    private const val chevroletCode = "23"

    private const val honda = "GM - Chevrolet"
    private const val hondaCode = "25"

    val brandResponseDtoLists = listOf(
        BrandResponseDto(
            chevroletCode,
            chevrolet
        ),
        BrandResponseDto(
            hondaCode,
            honda
        )
    )

    val brandsList = listOf(
        Brand(
            chevroletCode,
            chevrolet
        ),
        Brand(
            hondaCode,
            honda
        )
    )

    private const val modelNameOne = "ONIX SEDAN Plus LTZ 1.0 12V TB Flex Aut."
    private const val modelCodeOne = "8827"

    private const val modelNameTwo = "TRACKER LT 1.8 16V Flex 4x2 Aut."
    private const val modelCodeTwo = "7428"

    val brandModelsDtoList = listOf(
        BrandModelResponseDto(
            modelCodeOne,
            modelNameOne
        ),
        BrandModelResponseDto(
            modelCodeTwo,
            modelNameTwo
        )
    )

    val brandModelsList = listOf(
        BrandModel(
            modelCodeOne,
            modelNameOne
        ),
        BrandModel(
            modelCodeTwo,
            modelNameTwo
        )
    )

    private const val yearNameOne = "2016 Gasolina"
    private const val yearCodeOne = "2016-1"

    private const val yearNameTwo = "2015 Gasolina"
    private const val yearCodeTwo = "2015-1"

    val modelYearsResponseList = listOf(
        ModelYearResponseDto(
            yearNameOne,
            yearCodeOne
        ),
        ModelYearResponseDto(
            yearNameTwo,
            yearCodeTwo
        )
    )

    val modelYearsList = listOf(
        ModelYear(
            yearNameOne,
            yearCodeOne
        ),
        ModelYear(
            yearNameTwo,
            yearCodeTwo
        )
    )

    val vehicleResponseList = listOf(
        VehicleResponseDto(
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
        VehicleResponseDto(
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
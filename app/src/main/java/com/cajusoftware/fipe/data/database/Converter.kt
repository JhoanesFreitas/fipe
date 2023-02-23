package com.cajusoftware.fipe.data.database

import androidx.room.TypeConverter
import com.cajusoftware.fipe.data.database.dtos.PriceDto
import com.cajusoftware.fipe.utils.exts.toPriceDto

private const val separator = "-"

class Converter {

    @TypeConverter
    fun toList(values: String): List<PriceDto> {
        val array = values.split(separator)
        val pricesList = mutableListOf<PriceDto>()
        array.forEach {
            pricesList.add(it.toPriceDto())
        }
        return pricesList
    }

    @TypeConverter
    fun toString(values: List<PriceDto>): String {
        return values.joinToString(separator)
    }
}



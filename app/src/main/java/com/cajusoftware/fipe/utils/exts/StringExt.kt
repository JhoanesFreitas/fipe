package com.cajusoftware.fipe.utils.exts

import android.net.Uri
import androidx.core.net.toUri
import com.cajusoftware.fipe.BuildConfig
import com.cajusoftware.fipe.data.database.dtos.PriceDto

fun String.toUrlComplement() =
    this.replace(" - ", "-")
        .replace(" ", "-")
        .replace("ë", "e")
        .lowercase()


fun String.toImageUri(): Uri =
    BuildConfig.THUMB_URL
        .plus(this)
        .plus(".png")
        .toUri()
        .buildUpon()
        .scheme("https")
        .build()

fun String.toSafeFloat() =
    try {
        this
            .replace(".", "")
            .replace(",", ".")
            .replace("R$", "")
            .trim()
            .toFloat()
    } catch (e: NumberFormatException) {
        0F
    }

fun String.toPriceWithoutSymbol() = this.replace("R$", "").trim()

fun String.toPriceDto(): PriceDto {
    val price: String = this.substringAfter("price=").substringBefore(",")
    val month: String = this.substringAfter("month=").substringBefore(",")
    val reference: String = this.substringAfter("reference=").substringBefore(")")

    return PriceDto(price, month, reference)
}

fun String.toChartLabel(): String {
    val dateArray = this.split(" de ")
    return "${dateArray.first().substring(0, 3)}/${dateArray.last()}"
}
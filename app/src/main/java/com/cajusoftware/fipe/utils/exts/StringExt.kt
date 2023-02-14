package com.cajusoftware.fipe.utils.exts

import android.net.Uri
import androidx.core.net.toUri
import com.cajusoftware.fipe.BuildConfig

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
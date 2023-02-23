package com.cajusoftware.fipe.utils.exts

fun Long.toSafeFloat() =
    try {
        this.toFloat()
    } catch (e: NumberFormatException) {
        0F
    }
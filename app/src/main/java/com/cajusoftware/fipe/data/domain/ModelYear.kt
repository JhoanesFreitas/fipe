package com.cajusoftware.fipe.data.domain

data class ModelYear(val yearCode: String, val yearWithFuel: String) {
    fun yearToSearch() =
        this.yearCode.substringBefore("-")
}

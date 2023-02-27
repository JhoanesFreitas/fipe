package com.cajusoftware.fipe.ui.brands.adapters

import androidx.recyclerview.widget.AsyncListDiffer

interface DiffAdapter<T> {
    val asyncListDiff: AsyncListDiffer<T>
    fun submitList(list: List<T>?)
}
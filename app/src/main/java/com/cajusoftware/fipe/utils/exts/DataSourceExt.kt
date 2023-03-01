package com.cajusoftware.fipe.utils.exts

import androidx.paging.DataSource
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <Key : Any, Value : Any> DataSource.Factory<Key, Value>.toFlow(): Flow<PagingData<Value>> {
    return flow { emit(PagingData.from(emptyList())) }
}
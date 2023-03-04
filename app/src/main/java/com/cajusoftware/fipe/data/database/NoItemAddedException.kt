package com.cajusoftware.fipe.data.database

import android.content.res.Resources.NotFoundException

class NoItemAddedException(
    val itemCode: String,
    override val message: String = "Nenhum item foi adicionado até o momento!"
) : NotFoundException(message)
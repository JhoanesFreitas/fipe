package com.cajusoftware.fipe.utils.exts

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.gone() { this.visibility = GONE }

fun View.visible() { this.visibility = VISIBLE }
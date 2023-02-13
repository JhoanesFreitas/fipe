package com.cajusoftware.fipe.utils.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.cajusoftware.fipe.utils.exts.gone
import com.cajusoftware.fipe.utils.exts.visible

@BindingAdapter("app:visibility")
fun setImageViewVisibility(image: ImageView, isHomeFragment: Boolean) {
    if (isHomeFragment) image.visible()
    else image.gone()
}
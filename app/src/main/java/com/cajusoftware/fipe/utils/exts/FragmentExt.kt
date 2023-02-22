package com.cajusoftware.fipe.utils.exts

import androidx.fragment.app.FragmentActivity
import com.cajusoftware.fipe.ui.MainActivity

fun FragmentActivity.hideToolbar() {
    (this as? MainActivity)?.hideToolbar()
}

fun FragmentActivity.showToolbar() {
    (this as? MainActivity)?.showToolbar()
}

fun FragmentActivity.onNavigationClickListener(callback: () -> Unit) {
    (this as? MainActivity)?.onNavigationClickListener(callback)
}
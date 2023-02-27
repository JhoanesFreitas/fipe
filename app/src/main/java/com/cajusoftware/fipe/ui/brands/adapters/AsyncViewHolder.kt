package com.cajusoftware.fipe.ui.brands.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

abstract class AsyncViewHolder<T, U>(context: Context) : FrameLayout(context) {

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    var binding: T? = null

    private var isInflated = false
    private val bindingFunctions: MutableList<AsyncViewHolder<T, U>.() -> Unit> = mutableListOf()

    fun inflate(layoutId: Int = -1) {
        AsyncLayoutInflater(context).inflate(layoutId, this) { view, _, _ ->
            isInflated = true
            addView(createDataBindingView(view))
            bindView()
        }
    }

    private fun bindView() {
        with(bindingFunctions) {
            forEach { it() }
            clear()
        }
    }

    fun bindWhenInflated(bindFunc: AsyncViewHolder<T, U>.() -> Unit) {
        if (isInflated) {
            bindFunc()
        } else {
            bindingFunctions.add(bindFunc)
        }
    }

    open fun createDataBindingView(view: View): View? = view

    abstract fun bind(model: U)

}
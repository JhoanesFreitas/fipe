package com.cajusoftware.fipe.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.databinding.LayoutDescriptionViewBinding

class DescriptionView(context: Context, attributes: AttributeSet) :
    LinearLayout(context, attributes) {

    private val binding =
        LayoutDescriptionViewBinding.inflate(LayoutInflater.from(context), this, true)

    var key: CharSequence? = null
        set(value) {
            binding.title.text = value
            field = value
        }

    var value: CharSequence? = null
        set(value) {
            binding.value.text = value
            field = value
        }

    init {
        val typedArray = context.obtainStyledAttributes(attributes, R.styleable.DescriptionView)
        key = typedArray.getString(R.styleable.DescriptionView_key)
        value = typedArray.getString(R.styleable.DescriptionView_value)
        typedArray.recycle()
    }

}
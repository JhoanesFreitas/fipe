package com.cajusoftware.fipe.ui.components

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.databinding.LayoutSearchViewBinding
import com.cajusoftware.fipe.utils.exts.gone
import com.cajusoftware.fipe.utils.exts.visible

class SearchView(context: Context, attributes: AttributeSet) : LinearLayout(context, attributes) {

    private val binding = LayoutSearchViewBinding.inflate(LayoutInflater.from(context), this, true)

    var hint: CharSequence? = null
        set(value) {
            binding.editTextTextPersonName.hint = value
            field = value
        }

    var filterVisibility: Boolean = true
        set(value) {
            if (value) binding.filterButton.visible()
            else binding.filterButton.gone()
            field = value
        }

    init {

        val typedArray = context.obtainStyledAttributes(attributes, R.styleable.SearchView)
        hint = typedArray.getString(R.styleable.SearchView_hint)
        filterVisibility = typedArray.getBoolean(R.styleable.SearchView_filterVisibility, true)
        typedArray.recycle()

        binding.editTextTextPersonName.apply {
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }
}
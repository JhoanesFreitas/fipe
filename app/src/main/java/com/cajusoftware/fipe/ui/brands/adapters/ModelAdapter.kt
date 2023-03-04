package com.cajusoftware.fipe.ui.brands.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cajusoftware.fipe.data.domain.BrandsModel
import com.cajusoftware.fipe.databinding.BrandModelItemBinding
import com.cajusoftware.fipe.utils.exts.toUrlComplement

class ModelAdapter(
    private val onBrandNameListener: (String, (String) -> Unit) -> Unit,
    private val onClickListener: (BrandsModel) -> Unit
) : PagingDataAdapter<BrandsModel, ModelAdapter.BrandModelViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandModelViewHolder {
        return BrandModelViewHolder(
            BrandModelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun stateFlow(stateCallback: (CombinedLoadStates) -> Unit) {
        addLoadStateListener(stateCallback)
    }

    override fun onBindViewHolder(holder: BrandModelViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class BrandModelViewHolder(private val binding: BrandModelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BrandsModel) {
            var brandName = ""
            binding.subtitle.text = model.name

            onBrandNameListener(model.brandNumber) {
                binding.imgName = it.toUrlComplement()
                brandName = it
            }

            binding.modelsLayout.setOnClickListener {
                onClickListener(model.copy(brandName = brandName))
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BrandsModel>() {
        override fun areItemsTheSame(oldItem: BrandsModel, newItem: BrandsModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: BrandsModel, newItem: BrandsModel): Boolean {
            return oldItem == newItem
        }
    }
}
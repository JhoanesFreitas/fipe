package com.cajusoftware.fipe.ui.brands.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cajusoftware.fipe.data.domain.BrandsModel
import com.cajusoftware.fipe.databinding.BrandModelItemBinding
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel
import com.cajusoftware.fipe.utils.exts.toUrlComplement

class ModelAdapter(
    private val viewModel: VehicleBrandViewModel,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<BrandsModel, ModelAdapter.BrandModelViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandModelViewHolder {
        return BrandModelViewHolder(
            BrandModelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BrandModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<BrandsModel>?) {
        super.submitList(list)
        viewModel.setModelLoading(list.isNullOrEmpty())
    }

    inner class BrandModelViewHolder(private val binding: BrandModelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BrandsModel) {
            binding.subtitle.text = model.name
            viewModel.getBrandName(model.brandNumber).observe(lifecycleOwner) {
                binding.imgName = it.toUrlComplement()
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
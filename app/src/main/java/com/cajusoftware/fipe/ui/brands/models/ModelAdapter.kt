package com.cajusoftware.fipe.ui.brands.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cajusoftware.fipe.data.domain.BrandModel
import com.cajusoftware.fipe.databinding.BrandModelItemBinding
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel
import com.cajusoftware.fipe.utils.exts.toUrlComplement

class ModelAdapter(
    private val viewModel: VehicleBrandViewModel,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<BrandModel, ModelAdapter.BrandModelViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandModelViewHolder {
        return BrandModelViewHolder(BrandModelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BrandModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BrandModelViewHolder(private val binding: BrandModelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BrandModel) {
            binding.subtitle.text = model.name
            viewModel.getBrandName(model.brandNumber).observe(lifecycleOwner) {
                binding.imgName = it.toUrlComplement()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BrandModel>() {
        override fun areItemsTheSame(oldItem: BrandModel, newItem: BrandModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: BrandModel, newItem: BrandModel): Boolean {
            return oldItem == newItem
        }
    }
}
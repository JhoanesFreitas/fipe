package com.cajusoftware.fipe.ui.brands

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.databinding.BrandItemBinding
import com.cajusoftware.fipe.utils.exts.gone
import com.cajusoftware.fipe.utils.exts.toUrlComplement
import com.cajusoftware.fipe.utils.exts.visible

class BrandAdapter : ListAdapter<Brand, BrandAdapter.BrandViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(BrandItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(getItem(position).name)
    }

    class BrandViewHolder(private val binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(brandName: String) {
            binding.brandName.text = brandName
            binding.imgName = brandName.toUrlComplement()

            binding.brandLogo.apply {
                viewTreeObserver.addOnGlobalLayoutListener {
                    if (visibility == GONE) binding.brandName.visible()
                    else binding.brandName.gone()
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Brand>() {
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem == newItem
        }

    }
}
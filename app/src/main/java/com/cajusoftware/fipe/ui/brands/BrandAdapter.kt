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

class BrandAdapter(
    private val viewModel: VehicleBrandViewModel,
    private val clickCallback: (brandNumber: String) -> Unit
) :
    ListAdapter<Brand, BrandAdapter.BrandViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(BrandItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<Brand>?) {
        super.submitList(list)
        viewModel.setBrandLoading(list.isNullOrEmpty())
    }

    inner class BrandViewHolder(private val binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(brand: Brand) {
            binding.brandName.text = brand.name
            binding.imgName = brand.name.toUrlComplement()

            binding.brandLogo.apply {
                viewTreeObserver.addOnGlobalLayoutListener {
                    if (visibility == GONE) binding.brandName.visible()
                    else binding.brandName.gone()
                }
            }

            binding.container.setOnClickListener {
                clickCallback(brand.code)
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
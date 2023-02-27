package com.cajusoftware.fipe.ui.brands.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.data.domain.BrandsModel
import com.cajusoftware.fipe.databinding.BrandModelItemBinding
import com.cajusoftware.fipe.ui.brands.VehicleBrandViewModel
import com.cajusoftware.fipe.utils.exts.toUrlComplement

class ModelAdapter(
    private val viewModel: VehicleBrandViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onClickListener: ((BrandsModel) -> Unit)
) : RecyclerView.Adapter<ModelAdapter.BrandModelViewHolder>(), DiffAdapter<BrandsModel> {

    override val asyncListDiff: AsyncListDiffer<BrandsModel> =
        AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandModelViewHolder {
        return BrandModelViewHolder(BrandModelAsyncViewHolder(parent.context).apply {
            inflate(R.layout.brand_model_item)
        })
    }

    override fun getItemCount(): Int = asyncListDiff.currentList.size

    override fun onBindViewHolder(holder: BrandModelViewHolder, position: Int) {
        (holder.itemView as BrandModelAsyncViewHolder).bindWhenInflated {
            bind(asyncListDiff.currentList[position])
        }
    }

    override fun submitList(list: List<BrandsModel>?) {
        viewModel.setModelLoading(list.isNullOrEmpty())
        asyncListDiff.submitList(list)
    }

    inner class BrandModelViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent)

    inner class BrandModelAsyncViewHolder(context: Context) :
        AsyncViewHolder<BrandModelItemBinding, BrandsModel>(context) {

        override fun createDataBindingView(view: View): View? {
            binding = BrandModelItemBinding.bind(view)
            return binding?.root
        }

        override fun bind(model: BrandsModel) {
            var brandName = ""
            binding?.subtitle?.text = model.name
            viewModel.getBrandName(model.brandNumber).observe(lifecycleOwner) {
                binding?.imgName = it.toUrlComplement()
                brandName = it
            }

            binding?.modelsLayout?.setOnClickListener {
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
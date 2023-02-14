package com.cajusoftware.fipe.utils.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.data.domain.Brand
import com.cajusoftware.fipe.ui.brands.BrandAdapter
import com.cajusoftware.fipe.utils.exts.gone
import com.cajusoftware.fipe.utils.exts.toImageUri
import com.cajusoftware.fipe.utils.exts.visible

@BindingAdapter("app:visibility")
fun setImageViewVisibility(image: ImageView, isHomeFragment: Boolean) {
    if (isHomeFragment) image.visible()
    else image.gone()
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Brand>?
) {
    val adapter = recyclerView.adapter as BrandAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageName: String?) {
    imageName?.let {
        imageView.load(it.toImageUri()) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
            listener(
                onSuccess = { _, _ -> imageView.visible() },
                onError = { _, _ -> imageView.bindImages(imageName.split("-").last(), imageName) })
        }
    }
}

fun ImageView.bindImages(imageName: String?, fullImageName: String?) {
    imageName?.let {
        this.load(it.toImageUri()) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
            listener(
                onSuccess = { _, _ -> this@bindImages.visible() },
                onError = { _, _ ->
                    run {
                        val splitImageName = fullImageName?.split("-")
                        if ((splitImageName?.size ?: 0) > 1 && splitImageName?.last() == imageName)
                            bindImages(splitImageName.first(), fullImageName)
                        else
                            this@bindImages.gone()
                    }
                })
        }
    }
}

@BindingAdapter("canShow")
fun setTextVisibility(textView: TextView, canShow: Boolean) {
    if (canShow) textView.visible() else textView.gone()
}
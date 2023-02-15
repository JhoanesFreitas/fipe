package com.cajusoftware.fipe.utils.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.R.string.brand_name_description
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
    data: List<Nothing>?
) {
    (recyclerView.adapter as ListAdapter<*, *>).submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageName: String?) {
    imageName?.let {
        imageView.load(it.toImageUri()) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            listener(
                onSuccess = { _, _ ->
                    imageView.apply {
                        visible()
                        contentDescription =
                            imageView.context.getString(brand_name_description, imageName)
                    }
                },
                onError = { _, _ -> imageView.bindImages(imageName.split("-").last(), imageName) })
        }
    }
}

fun ImageView.bindImages(imageName: String?, fullImageName: String?) {
    imageName?.let {
        this.load(it.toImageUri()) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            listener(
                onSuccess = { _, _ ->
                    this@bindImages.apply {
                        visible()
                        contentDescription =
                            context.getString(
                                brand_name_description,
                                imageName
                            )
                    }
                },
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
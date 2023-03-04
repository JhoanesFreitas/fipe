package com.cajusoftware.fipe.utils.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.R.string.brand_name_description
import com.cajusoftware.fipe.data.domain.Historic
import com.cajusoftware.fipe.data.network.ConnectivityStatus
import com.cajusoftware.fipe.data.network.ConnectivityStatus.OFFLINE
import com.cajusoftware.fipe.ui.components.ChartView
import com.cajusoftware.fipe.utils.exts.*
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@BindingAdapter("app:visibility")
fun setViewVisibility(view: View, isVisible: Boolean) {
    if (isVisible) view.visible()
    else view.gone()
}

@BindingAdapter("app:animation")
fun setShimmerVisibility(view: ShimmerFrameLayout, isVisible: Boolean) {
    if (isVisible) {
        view.startShimmer()
        view.visible()
    } else {
        view.stopShimmer()
        view.gone()
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Nothing>?,
) {
    (recyclerView.adapter as? ListAdapter<*, *>)?.submitList(data)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("listDataPaging", "scope")
fun bindRecyclerViewPaging(
    recyclerView: RecyclerView,
    data: PagingData<Nothing>?,
    scope: CoroutineScope?
) {
    (recyclerView.adapter as PagingDataAdapter<Nothing, *>).apply {
        scope?.launch { data?.let { this@apply.submitData(it) } }
    }
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

@BindingAdapter("isNoConnection")
fun showConnectionStatus(textView: TextView, connectStatus: ConnectivityStatus?) {
    val status = connectStatus ?: return

    textView.apply {
        when (status) {
            OFFLINE -> {
                backgroundTintList = ColorStateList.valueOf(Color.RED)
                text = context.getString(R.string.no_connection_title)
                visible()
            }
            else -> {
                backgroundTintList = ColorStateList.valueOf(Color.parseColor("#228B22"))
                text = context.getString(R.string.back_online)
                Handler(Looper.getMainLooper()).postDelayed({ gone() }, 2000)
            }
        }
    }
}

@BindingAdapter("values")
fun setValues(view: ChartView, values: Historic?) {
    view.historic = values
}
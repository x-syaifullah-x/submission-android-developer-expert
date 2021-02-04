package com.android.developer.expert.databinding.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.android.developer.expert.GlideApp
import com.android.developer.expert.R
import com.android.developer.expert.databinding.TheMovieDBImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.signature.ObjectKey


object ItemAdapter {
    @JvmStatic
    @BindingAdapter("set_image")
    fun setImage(appCompatImageView: AppCompatImageView, path: String?) {

        appCompatImageView.rootView.clipToOutline = true
        if (path?.isNotBlank() == true) {
            GlideApp.with(appCompatImageView.context)
                .load(TheMovieDBImage.url(path = path))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(ObjectKey(path))
                .placeholder(R.drawable.ic_item_image_placeholder)
                .transform(
                    GranularRoundedCorners(
                        appCompatImageView.resources.getDimension(R.dimen._15sdp),
                        appCompatImageView.resources.getDimension(R.dimen._15sdp),
                        0f,
                        0f,
                    )
                )
                .into(appCompatImageView)
        }
    }
}
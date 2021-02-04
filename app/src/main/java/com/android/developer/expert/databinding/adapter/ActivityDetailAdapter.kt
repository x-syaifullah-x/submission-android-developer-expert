package com.android.developer.expert.databinding.adapter

import android.R.drawable.ic_menu_delete
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.android.developer.expert.GlideApp
import com.android.developer.expert.R
import com.android.developer.expert.core.domain.model.GenreModel
import com.android.developer.expert.databinding.TheMovieDBImage
import com.android.developer.expert.presentation.detail.DetailActivity
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.appbar.CollapsingToolbarLayout

object ActivityDetailAdapter {

    @JvmStatic
    @BindingAdapter("set_title_collapsing_toolbar_in_fragment")
    fun setTitleCollapsingToolbarInFragment(view: View, title: String?) {
        val collapsingToolbarLayout =
            view.rootView.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = title
    }

    @JvmStatic
    @BindingAdapter("setup_app_bar_activity")
    fun setAppBar(view: View, path: String?) {
        val activity = (view.context as DetailActivity)
        val binding = activity.binding

        path?.apply {
            binding.progressBar.visibility = View.VISIBLE
            GlideApp.with(view.context)
                .load(TheMovieDBImage.url(path = path))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(ObjectKey(path))
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        val palette = Palette.Builder(resource!!.toBitmap()).generate()
                        palette.getDominantColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.design_default_color_primary
                            )
                        ).apply {
                            activity.window.statusBarColor = this
                            binding.collapsingToolbar.setContentScrimColor(this)
                            val appCompatRatingBar =
                                activity.findViewById<AppCompatRatingBar>(R.id.user_score_content)
                            appCompatRatingBar.progressDrawable.setTint(this)
                        }
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                }).into(binding.posterDetail)
        }
    }

    @JvmStatic
    @BindingAdapter("set_genres")
    fun setGenres(view: AppCompatTextView, genres: List<GenreModel>?) {
        val genre = StringBuilder()
        genres?.forEachIndexed { i, v ->
            genre.append(if (i != genres.size - 1) "${v.name}, " else v.name)
        }
        view.text = genre.toString()
    }

    @JvmStatic
    @BindingAdapter("setup_fab_detail_activity")
    fun setFloatingAdd(view: View, isFavorite: Boolean) {
        val binding = (view.context as DetailActivity).binding
        val imageDrawable = ContextCompat.getDrawable(
            view.context, (if (isFavorite) ic_menu_delete else android.R.drawable.ic_menu_add)
        )
        binding.fab.setImageDrawable(imageDrawable)
    }
}
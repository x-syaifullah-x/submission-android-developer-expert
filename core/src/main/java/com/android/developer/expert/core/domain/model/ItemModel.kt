package com.android.developer.expert.core.domain.model

import android.os.Parcelable
import com.android.developer.expert.core.domain.model.base.IModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    val name: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Float,
    override val id: Int
) : IModel<Int>, Parcelable
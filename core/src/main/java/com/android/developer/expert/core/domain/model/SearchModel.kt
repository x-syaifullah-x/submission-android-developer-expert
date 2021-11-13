package com.android.developer.expert.core.domain.model

import android.os.Parcelable
import com.android.developer.expert.core.domain.model.base.IModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchModel(
    override val id: Int?,
    val mediaType: String?,
    val title: String?
) : IModel<Int>, Parcelable
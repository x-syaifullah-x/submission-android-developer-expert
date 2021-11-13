package com.android.developer.expert.core.domain.model

import com.android.developer.expert.core.domain.model.base.IDetailModel

data class DetailTvModel(
    override val id: Int,
    override val backdropPath: String,
    override val originalLanguage: String,
    override val overview: String,
    override val urlPosterPath: String,
    override val title: String,
    override val voteAverage: Float,
    override val genres: List<GenreModel>,
    override val recommendation: List<ItemModel>,
    override val isFavorite: Boolean,

    val firstAirDate: String,
    val lastAirDate: String,
) : IDetailModel
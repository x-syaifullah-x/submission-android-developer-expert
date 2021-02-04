package com.android.developer.expert.core.domain.model

import com.android.developer.expert.core.domain.model.base.IDetailModel

data class DetailMovieModel(
    override val id: Int,
    override val backdropPath: String,
    override val originalLanguage: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String?,
    override val title: String,
    override val voteAverage: Float,
    override val voteCount: Int,
    override val genres: List<GenreModel>,
    override val recommendation: List<ItemModel>,
    override val isFavorite: Boolean,

    val releaseDate: String,
) : IDetailModel
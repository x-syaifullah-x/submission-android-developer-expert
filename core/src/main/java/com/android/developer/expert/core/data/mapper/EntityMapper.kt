package com.android.developer.expert.core.data.mapper

import com.android.developer.expert.core.data.source.local.base.IResultEntity
import com.android.developer.expert.core.data.source.local.entity.movie.Movie
import com.android.developer.expert.core.data.source.local.entity.search.SearchEntity
import com.android.developer.expert.core.data.source.local.entity.tv.Tv
import com.android.developer.expert.core.domain.model.*
import com.android.developer.expert.core.domain.model.base.IModel

fun Movie?.toDetailMovieModel() = DetailMovieModel(
    id = this?.id() ?: IModel.DATA_NOT_VALID,
    backdropPath = this?.backdropPath() ?: "-",
    genres = this?.genres()?.map { GenreModel(id = it.id, it.name) } ?: listOf(),
    originalLanguage = this?.originalLanguage() ?: "-",
    overview = this?.overview() ?: "-",
    popularity = this?.popularity() ?: 0.0,
    posterPath = this?.posterPath(),
    title = this?.title() ?: "-",
    releaseDate = this?.releaseDate() ?: "-",
    voteAverage = this?.voteAverage() ?: 0f,
    voteCount = this?.voteCount() ?: 0,
    recommendation = this?.recommendations()?.results()
        ?.map { it.toItemModel(it.title) } ?: listOf(),
    isFavorite = this?.isFavorite() == true
)


fun Tv?.toDetailTvModel() = DetailTvModel(
    id = this?.id() ?: IModel.DATA_NOT_VALID,
    backdropPath = this?.backdropPath() ?: "-",
    genres = this?.genres()?.map { GenreModel(id = it.id, it.name) } ?: listOf(),
    originalLanguage = this?.originalLanguage() ?: "-",
    overview = this?.overview() ?: "-",
    popularity = this?.popularity() ?: 0.0,
    posterPath = this?.posterPath(),
    title = this?.originalName() ?: "-",
    voteAverage = this?.voteAverage() ?: 0f,
    voteCount = this?.voteCount() ?: 0,
    recommendation = this?.recommendations()?.results()
        ?.map { it.toItemModel(it.name) } ?: listOf(),
    isFavorite = this?.isFavorite() == true,
    firstAirDate = this?.firstAirDate() ?: "-",
    lastAirDate = this?.lastAirDate() ?: "-"
)

fun SearchEntity?.toSearchModel() = SearchModel(
    id = this?.primaryKey,
    title = this?.title,
    mediaType = this?.mediaType
)

fun <T : IResultEntity> T.toItemModel(name: String?) = ItemModel(
    id = id,
    name = name ?: "-",
    overview = overview ?: "-",
    posterPath = posterPath,
    voteAverage = voteAverage,
)
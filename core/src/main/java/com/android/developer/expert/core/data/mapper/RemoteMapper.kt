package com.android.developer.expert.core.data.mapper

import com.android.developer.expert.core.data.source.local.base.IPageEmbed
import com.android.developer.expert.core.data.source.local.entity.discover.movie.DiscoverMovieResultEntity
import com.android.developer.expert.core.data.source.local.entity.discover.tv.DiscoverTvResultEntity
import com.android.developer.expert.core.data.source.local.entity.movie.MovieEntity
import com.android.developer.expert.core.data.source.local.entity.movie.MovieGenreEntity
import com.android.developer.expert.core.data.source.local.entity.recommendation.movie.RecommendationMovieResultEntity
import com.android.developer.expert.core.data.source.local.entity.recommendation.tv.RecommendationTvResultEntity
import com.android.developer.expert.core.data.source.local.entity.search.SearchEntity
import com.android.developer.expert.core.data.source.local.entity.tv.TvEntity
import com.android.developer.expert.core.data.source.local.entity.tv.TvGenreEntity
import com.android.developer.expert.core.data.source.remote.response.MovieResponse
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.TvResponse
import com.android.developer.expert.core.data.source.remote.response.model.*

fun MultiResult.toSearchEntity() = SearchEntity(
    primaryKey = id,
    title = title ?: originalTitle ?: originalName ?: name ?: "-",
    mediaType = mediaType
)

fun MovieResult.toDiscoverMovieResultEntity(fk: Int) = DiscoverMovieResultEntity(
    foreignKey = fk,
    popularity = popularity,
    voteCount = voteCount,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    voteAverage = voteAverage,
    overview = overview,
    id = id,
    releaseDate = releaseDate ?: "",
    video = video,
    adult = adult,
    originalTitle = originalTitle,
    title = title
)

fun TvResult.toDiscoverTvResultEntity(fk: Int) = DiscoverTvResultEntity(
    foreignKey = fk,
    popularity = popularity,
    voteCount = voteCount,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    voteAverage = voteAverage,
    overview = overview,
    id = id,
    firstAirDate = firstAirDate,
    name = name,
    originalName = originalName
)

fun MovieResult.toRecommendationMovieResultEntity(fk: Int) = RecommendationMovieResultEntity(
    primaryKey = id,
    foreignKey = fk,
    popularity = popularity,
    voteCount = voteCount,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    voteAverage = voteAverage,
    overview = overview,
    id = id,
    releaseDate = releaseDate ?: "",
    video = video,
    adult = adult,
    originalTitle = originalTitle,
    title = title
)

fun TvResult.toRecommendationMovieResultEntity(fk: Int) = RecommendationTvResultEntity(
    primaryKey = id,
    foreignKey = fk,
    popularity = popularity,
    voteCount = voteCount,
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    voteAverage = voteAverage,
    overview = overview,
    id = id,
    firstAirDate = firstAirDate,
    name = name,
    originalName = originalName
)

fun Genres.toMovieGenreEntity() = MovieGenreEntity(id = id, name = name, foreignKey = 0)

fun Genres.toTvGenreEntity() = TvGenreEntity(id = id, name = name, foreignKey = 0)

fun TvWithTvRecommendation.toTvEntity() = TvEntity(
    primaryKey = id,
    backdropPath = backdropPath,
    homepage = homepage,
    originalLanguage = originalLanguage,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    status = status,
    tagLine = tagLine,
    voteAverage = voteAverage,
    voteCount = voteCount,
    firstAirDate = firstAirDate,
    inProduction = inProduction,
    lastAirDate = lastAirDate,
    name = name,
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originalName = originalName,
    type = type,
    id = id
)

fun MovieWithRecommendation.toMovieEntity() = MovieEntity(
    primaryKey = id,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    homepage = homepage,
    imDbId = imDbId,
    originalLanguage = originalLanguage,
    title = title,
    releaseDate = releaseDate,
    overview = overview,
    voteAverage = voteAverage,
    originalTitle = originalTitle,
    posterPath = posterPath,
    voteCount = voteCount,
    popularity = popularity,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagLine = tagLine,
    video = video,
    id = id
)

fun MovieResponse.toMovieWithRecommendation(movieResults: List<PageResponse<MovieResult>>) =
    MovieWithRecommendation(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        homepage = homepage,
        imDbId = imDbId,
        originalLanguage = originalLanguage,
        title = title,
        releaseDate = releaseDate,
        overview = overview,
        voteAverage = voteAverage,
        originalTitle = originalTitle,
        posterPath = posterPath,
        voteCount = voteCount,
        popularity = popularity,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagLine = tagLine,
        video = video,
        belongsToCollection = belongsToCollection,
        genres = genres,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        spokenLanguages = spokenLanguages,
        recommendations = movieResults,
    )

fun TvResponse.toTvWithTvRecommendation(lis: List<PageResponse<TvResult>>) = TvWithTvRecommendation(
    backdropPath = backdropPath,
    genres = genres,
    homepage = homepage,
    id = id,
    originalLanguage = originalLanguage,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies,
    productionCountries = productionCountries,
    status = status,
    tagLine = tagLine,
    voteAverage = voteAverage,
    voteCount = voteCount,
    createdBy = createdBy,
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate,
    inProduction = inProduction,
    languages = languages,
    lastAirDate = lastAirDate,
    lastEpisodeToAir = lastEpisodeToAir,
    name = name,
    nextEpisodeToAir = nextEpisodeToAir,
    networks = networks,
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originCountry = originCountry,
    originalName = originalName,
    seasons = seasons,
    type = type,
    recommendations = lis
)

fun PageResponse<MovieResult>.toPageEmbedded() =
    object : IPageEmbed<RecommendationMovieResultEntity> {
        override fun page() = page
        override fun totalPages() = totalPages
        override fun totalResult() = totalResults
        override fun results() = results.map { it.toRecommendationMovieResultEntity(0) }
    }

@JvmName("toPageEmbeddedTvResult")
fun PageResponse<TvResult>.toPageEmbedded() = object : IPageEmbed<RecommendationTvResultEntity> {
    override fun page() = page
    override fun totalPages() = totalPages
    override fun totalResult() = totalResults
    override fun results() = results.map { it.toRecommendationMovieResultEntity(0) }
}
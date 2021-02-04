package com.android.developer.expert.core.data.source.local.entity.movie

import androidx.room.Embedded
import androidx.room.Relation
import com.android.developer.expert.core.data.source.local.base.IDetail
import com.android.developer.expert.core.data.source.local.entity.recommendation.movie.RecommendationMovie
import com.android.developer.expert.core.data.source.local.entity.recommendation.movie.RecommendationMovieEntity

class Movie(
    @Embedded
    private val movieEntity: MovieEntity,

    @Relation(
        parentColumn = MovieEntity.PRIMARY_KEY,
        entityColumn = MovieGenreEntity.FOREIGN_KEY,
        entity = MovieGenreEntity::class
    )
    private val genres: List<MovieGenreEntity>?,

    @Relation(
        parentColumn = MovieEntity.PRIMARY_KEY,
        entityColumn = RecommendationMovieEntity.ID,
        entity = RecommendationMovieEntity::class,
    )
    private val recommendations: RecommendationMovie?
) : IDetail {
    override fun id(): Int = movieEntity.primaryKey
    override fun backdropPath(): String? = movieEntity.backdropPath
    override fun homepage(): String? = movieEntity.homepage
    override fun originalLanguage(): String = movieEntity.originalLanguage
    override fun overview(): String = movieEntity.overview
    override fun popularity(): Double = movieEntity.popularity
    override fun posterPath(): String? = movieEntity.posterPath
    override fun status(): String = movieEntity.status
    override fun tagLine(): String? = movieEntity.tagLine
    override fun voteAverage(): Float = movieEntity.voteAverage
    override fun voteCount(): Int = movieEntity.voteCount

    fun genres(): List<MovieGenreEntity>? = genres
    fun adult(): Boolean = movieEntity.adult
    fun originalTitle(): String? = movieEntity.originalTitle
    fun releaseDate(): String? = movieEntity.releaseDate
    fun title(): String? = movieEntity.title
    fun video(): Boolean = movieEntity.video
    fun recommendations() = recommendations
    fun isFavorite() = movieEntity.isFavorite
}
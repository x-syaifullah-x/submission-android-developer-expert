package com.android.developer.expert.core.data.source.local.entity.recommendation.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.android.developer.expert.core.data.source.local.base.IMovieResultEntity
import com.android.developer.expert.core.data.source.local.entity.movie.MovieEntity
import com.android.developer.expert.core.data.source.local.entity.recommendation.movie.RecommendationMovieResultEntity.Companion.FOREIGN_KEY
import com.android.developer.expert.core.data.source.local.entity.recommendation.movie.RecommendationMovieResultEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
//    indices = [Index(value = [FOREIGN_KEY], unique = true)],
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = [MovieEntity.PRIMARY_KEY],
        childColumns = [FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)
data class RecommendationMovieResultEntity(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    override val primaryKey: Int,
    override val popularity: Double,
    override val voteCount: Int,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val originalLanguage: String,
    override val voteAverage: Float,
    override val overview: String,

    @ColumnInfo(name = FOREIGN_KEY)
    override val foreignKey: Int,
    override val id: Int,
    override val releaseDate: String,
    override val video: Boolean,
    override val adult: Boolean,
    override val originalTitle: String,
    override val title: String,
) : IMovieResultEntity {
    companion object {
        const val PRIMARY_KEY = "primary_key"
        const val TABLE_NAME = "movie_recommendation_result"
        const val FOREIGN_KEY = "foreign_key"
    }
}
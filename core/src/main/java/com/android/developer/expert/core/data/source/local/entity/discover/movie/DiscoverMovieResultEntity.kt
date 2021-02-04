package com.android.developer.expert.core.data.source.local.entity.discover.movie

import androidx.room.*
import com.android.developer.expert.core.data.source.local.base.IMovieResultEntity
import com.android.developer.expert.core.data.source.local.entity.discover.movie.DiscoverMovieResultEntity.Companion.FOREIGN_KEY
import com.android.developer.expert.core.data.source.local.entity.discover.movie.DiscoverMovieResultEntity.Companion.ID
import com.android.developer.expert.core.data.source.local.entity.discover.movie.DiscoverMovieResultEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(value = [ID, FOREIGN_KEY], unique = true)],
    foreignKeys = [ForeignKey(
        entity = DiscoverMovieEntity::class,
        parentColumns = [DiscoverMovieEntity.PAGE],
        childColumns = [FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)
data class DiscoverMovieResultEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PRIMARY_KEY)
    override val primaryKey: Int? = null,
    override val popularity: Double,
    override val voteCount: Int,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val originalLanguage: String,
    override val voteAverage: Float,
    override val overview: String,

    @ColumnInfo(name = FOREIGN_KEY)
    override val foreignKey: Int,
    @ColumnInfo(name = ID)
    override val id: Int,
    override val releaseDate: String,
    override val video: Boolean,
    override val adult: Boolean,
    override val originalTitle: String,
    override val title: String,
) : IMovieResultEntity {
    companion object {
        const val ID = "id"
        const val FOREIGN_KEY = "foreign_key"
        const val PRIMARY_KEY = "primary_key"
        const val TABLE_NAME = "discover_movie_result"
    }
}
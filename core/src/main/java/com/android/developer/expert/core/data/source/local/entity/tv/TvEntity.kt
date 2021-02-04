package com.android.developer.expert.core.data.source.local.entity.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.developer.expert.core.data.source.local.base.IResultEntity

@Entity(
    tableName = TvEntity.TABLE_NAME,
)
data class TvEntity(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    override val primaryKey: Int,
    override val backdropPath: String?,
    val homepage: String?,
    override val originalLanguage: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String?,
    val status: String,
    val tagLine: String?,
    override val voteAverage: Float,
    override val voteCount: Int,
    val firstAirDate: String,
    val inProduction: Boolean,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originalName: String,
    val type: String,
    @ColumnInfo(name = IS_FAVORITE)
    val isFavorite: Boolean = false,
    override val id: Int
) : IResultEntity {
    companion object {
        const val TABLE_NAME = "tv"
        const val PRIMARY_KEY = "primary_key"
        const val IS_FAVORITE = "is_favorite"
    }
}
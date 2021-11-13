package com.android.developer.expert.core.data.source.local.entity.discover.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.developer.expert.core.data.source.local.base.IPage

@Entity(
    tableName = DiscoverMovieEntity.TABLE_NAME
)
class DiscoverMovieEntity(
    @ColumnInfo(name = PAGE)
    @PrimaryKey
    override val page: Int,
    override val totalResults: Int,
    override val totalPages: Int,
) : IPage {
    companion object {
        const val PAGE = "page"
        const val TABLE_NAME = "discover_movie"
    }
}
package com.android.developer.expert.core.data.source.local

import androidx.room.withTransaction
import com.android.developer.expert.core.data.mapper.toDiscoverMovieResultEntity
import com.android.developer.expert.core.data.source.local.base.IDiscoverDataSource
import com.android.developer.expert.core.data.source.local.dao.MovieDao
import com.android.developer.expert.core.data.source.local.entity.discover.movie.DiscoverMovieEntity
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.model.MovieResult
import javax.inject.Inject

class DiscoverMovieDataSource @Inject constructor(
    private val dao: MovieDao
) : IDiscoverDataSource<DiscoverMovieEntity, MovieResult> {
    override suspend fun clearDiscover() = dao.clearDiscoverMovie()

    override suspend fun getPreviousPage() = dao.getLastPage()?.run {
        if (page == 1) null else page.minus(1)
    }

    override suspend fun getCurrentPage() = dao.getLastPage()

    override suspend fun getNextPage() = dao.getLastPage()?.run {
        if (page == totalPages) null else page.plus(1)
    }

    override suspend fun insert(data: PageResponse<MovieResult>) = dao.db.withTransaction {
        val discoverMovie = DiscoverMovieEntity(
            page = data.page, totalPages = data.totalPages, totalResults = data.totalResults
        )
        val discoverMovieResults = data.results.map { it.toDiscoverMovieResultEntity(data.page) }
        val resultDiscoverMovie = dao.insert(discoverMovie).toInt() != 0
        val resultDiscoverMovieResult = dao.insertDiscoverResults(discoverMovieResults).contains(0)
        return@withTransaction resultDiscoverMovie && resultDiscoverMovieResult
    }
}
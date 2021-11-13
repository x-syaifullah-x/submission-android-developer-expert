package com.android.developer.expert.core.data.source.local

import androidx.room.withTransaction
import com.android.developer.expert.core.data.mapper.toDiscoverTvResultEntity
import com.android.developer.expert.core.data.source.local.base.IDiscoverDataSource
import com.android.developer.expert.core.data.source.local.dao.TvDao
import com.android.developer.expert.core.data.source.local.entity.discover.tv.DiscoverTvEntity
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.model.TvResult
import javax.inject.Inject

class DiscoverTvDataSource @Inject constructor(
    private val dao: TvDao
) : IDiscoverDataSource<DiscoverTvEntity, TvResult> {
    override suspend fun clearDiscover() = dao.clearDiscoverMovie()

    override suspend fun getPreviousPage() = dao.getLastPage()?.run {
        if (page == 1) null else page.minus(1)
    }

    override suspend fun getCurrentPage() = dao.getLastPage()

    override suspend fun getNextPage() = dao.getLastPage()?.run {
        if (page == totalPages) null else page.plus(1)
    }

    override suspend fun insert(data: PageResponse<TvResult>) = dao.db.withTransaction {
        val discoverMovie = DiscoverTvEntity(
            page = data.page, totalPages = data.totalPages, totalResults = data.totalResults
        )
        val discoverTvResults = data.results
            .map { it.toDiscoverTvResultEntity(data.page) }
        val resultDiscoverTv = dao.insert(discoverMovie).toInt() != 0
        val resultDiscoverTvResult = dao.insertDiscoverResults(discoverTvResults).contains(0)
        return@withTransaction resultDiscoverTv && resultDiscoverTvResult
    }
}
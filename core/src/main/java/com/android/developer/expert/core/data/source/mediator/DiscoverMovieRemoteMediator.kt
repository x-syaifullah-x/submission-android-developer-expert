package com.android.developer.expert.core.data.source.mediator

import androidx.paging.ExperimentalPagingApi
import com.android.developer.expert.core.data.source.local.DiscoverMovieDataSource
import com.android.developer.expert.core.data.source.local.entity.discover.movie.DiscoverMovieResultEntity
import com.android.developer.expert.core.data.source.remote.RemoteMovieDataSource
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.model.MovieResult
import javax.inject.Inject

@ExperimentalPagingApi
class DiscoverMovieRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource,
    private val discoverDataSource: DiscoverMovieDataSource,
) : DiscoverRemoteMediator<Int, DiscoverMovieResultEntity, MovieResult>() {

    override suspend fun nextPage() =
        discoverDataSource.getNextPage()

    override suspend fun fetch(page: Int) =
        remoteDataSource.getDiscover(page)

    override suspend fun clearDiscover() =
        discoverDataSource.clearDiscover()

    override suspend fun saveFetch(pageResponse: PageResponse<MovieResult>) =
        discoverDataSource.insert(pageResponse)
}
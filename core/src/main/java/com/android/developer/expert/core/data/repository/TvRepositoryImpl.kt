package com.android.developer.expert.core.data.repository

import androidx.paging.*
import com.android.developer.expert.core.data.mapper.*
import com.android.developer.expert.core.data.source.local.TvDataSource
import com.android.developer.expert.core.data.source.mediator.DiscoverTvRemoteMediator
import com.android.developer.expert.core.data.source.mediator.networkBound
import com.android.developer.expert.core.data.source.remote.RemoteTvDataSource
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class TvRepositoryImpl @Inject constructor(
    private val remoteTv: RemoteTvDataSource,
    private val tv: TvDataSource,
    private val discoverTvRemoteMediator: DiscoverTvRemoteMediator
) : TvRepository {

    override fun getDiscoverTv(): Flow<PagingData<ItemModel>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        remoteMediator = discoverTvRemoteMediator,
        pagingSourceFactory = { tv.getTvResult() }
    ).flow.map { it.map { data -> data.toItemModel(data.name) } }

    override fun getTv(id: Int) = networkBound(
        fetch = { remoteTv.getTvWithTvRecommendation(id) },
        loadFromDB = { tv.getTv(id).map { it.toDetailTvModel() } },
        saveFetchResult = {
            val tvEntity = it.toTvEntity()
            val genres = it.genres.map { data -> data.toTvGenreEntity() }
            val recommendations = it.recommendations.map { data -> data.toPageEmbedded() }
            tv.insert(tvEntity, genres, recommendations)
        }
    )
}
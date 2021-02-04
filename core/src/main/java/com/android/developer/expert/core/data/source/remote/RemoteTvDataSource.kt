package com.android.developer.expert.core.data.source.remote

import com.android.developer.expert.core.data.mapper.toTvWithTvRecommendation
import com.android.developer.expert.core.data.source.remote.network.ApiResponse
import com.android.developer.expert.core.data.source.remote.network.ApiService
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.TvResponse
import com.android.developer.expert.core.data.source.remote.response.model.TvResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteTvDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteBaseDataSource<TvResult>() {

    public override suspend fun getDiscover(page: Int) =
        getPage { apiService.getDiscoverTv(page = page) }

    @Suppress("UNCHECKED_CAST")
    suspend fun getTvWithTvRecommendation(id: Int) = flow {
        val result = withContext(Dispatchers.IO) {
            val tv = async { apiService.getTv(id) }
            val recommendation = async {
                getPages { page -> apiService.getRecommendationTv(id, page) }
            }
            awaitAll(tv, recommendation)
        }

        val tvResult = result[0]
        val recommendationTvResult = result[1] as List<PageResponse<TvResult>>
        if (tvResult is TvResponse) {
            val movieWithTvRecommendation =
                tvResult.toTvWithTvRecommendation(recommendationTvResult)
            emit(ApiResponse.Success(movieWithTvRecommendation))
        } else {
            emit(ApiResponse.Empty)
        }
    }.catch { emit(ApiResponse.Error(it)) }.flowOn(Dispatchers.IO)
}
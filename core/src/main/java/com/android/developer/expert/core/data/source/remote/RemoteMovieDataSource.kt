package com.android.developer.expert.core.data.source.remote

import com.android.developer.expert.core.data.mapper.toMovieWithRecommendation
import com.android.developer.expert.core.data.source.remote.network.ApiResponse
import com.android.developer.expert.core.data.source.remote.network.ApiService
import com.android.developer.expert.core.data.source.remote.response.MovieResponse
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteBaseDataSource<MovieResult>() {

    public override suspend fun getDiscover(page: Int) =
        getPage { apiService.getDiscoverMovie(page = page) }


    @Suppress("UNCHECKED_CAST")
    suspend fun getMovieWithMovieRecommendation(id: Int) = flow {
        val result = withContext(Dispatchers.IO) {
            val movie = async { apiService.getMovie(id) }
            val recommendation = async {
                getPages { page -> apiService.getRecommendationMovie(id, page) }
            }
            awaitAll(movie, recommendation)
        }

        val movieResult = result[0]
        val recommendationMovieResult = result[1] as List<PageResponse<MovieResult>>
        if (movieResult is MovieResponse) {
            val movieWithMovieRecommendation =
                movieResult.toMovieWithRecommendation(recommendationMovieResult)
            emit(ApiResponse.Success(movieWithMovieRecommendation))
        } else {
            emit(ApiResponse.Empty)
        }
    }.catch { emit(ApiResponse.Error(it)) }.flowOn(Dispatchers.IO)
}
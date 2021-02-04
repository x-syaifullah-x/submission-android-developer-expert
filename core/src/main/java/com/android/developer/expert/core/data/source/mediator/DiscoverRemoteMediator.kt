package com.android.developer.expert.core.data.source.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.android.developer.expert.core.data.source.local.base.IResultEntity
import com.android.developer.expert.core.data.source.remote.network.ApiResponse
import com.android.developer.expert.core.data.source.remote.response.PageResponse
import com.android.developer.expert.core.data.source.remote.response.base.IResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

@ExperimentalPagingApi
abstract class DiscoverRemoteMediator<Key : Any, LocalResult : IResultEntity, RemoteResult : IResult> :
    RemoteMediator<Key, LocalResult>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val MIN_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 500
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Key, LocalResult>
    ): MediatorResult {

        val firstLoaded = state.isEmpty()
        val page = when (loadType) {
            LoadType.REFRESH -> if (firstLoaded) STARTING_PAGE_INDEX else nextPage()
                ?: STARTING_PAGE_INDEX
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> nextPage()
                ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        if (page > MAX_PAGE_SIZE) return MediatorResult.Success(endOfPaginationReached = true)

        return try {
            val results = withContext(Dispatchers.IO) {
                val pageSize = state.config.pageSize
                if (pageSize < MIN_PAGE_SIZE) throw Exception("minimum pageSize $MIN_PAGE_SIZE")
                if (pageSize % MIN_PAGE_SIZE != 0) throw Exception("pageSize 20..40..60..plus $MIN_PAGE_SIZE")
                List(pageSize / 20) {
                    async {
                        val currentPage = if (it == 0) page else page.plus(1)
                        when (val result = fetch(currentPage)) {
                            is ApiResponse.Success -> result.data
                            is ApiResponse.Empty -> null
                            is ApiResponse.Error -> throw RuntimeException(result.throwable)
                        }
                    }
                }.run { awaitAll(*toTypedArray()) }
            }

            if (loadType == LoadType.REFRESH && firstLoaded) clearDiscover()

            var endOfPaginationReached = true
            results.forEach {
                it?.apply {
                    endOfPaginationReached = it.results.isEmpty()
                    withContext(Dispatchers.IO) { saveFetch(it) }
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    abstract suspend fun nextPage(): Int?

    abstract suspend fun fetch(page: Int): ApiResponse<PageResponse<RemoteResult>>

    abstract suspend fun clearDiscover(): Int

    abstract suspend fun saveFetch(pageResponse: PageResponse<RemoteResult>): Boolean
}
package com.android.developer.expert.core.data.source.remote

import com.android.developer.expert.core.data.source.remote.network.ApiResponse
import com.android.developer.expert.core.data.source.remote.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteSearchDataSource @Inject constructor(private val apiService: ApiService) {

    fun search(query: String) = flow {
        val result = ApiResponse.fetch {
            if (query.isEmpty()) return@fetch ApiResponse.Empty
            val pageResponse = apiService.multiSearch(query)
            if (pageResponse.results.isNotEmpty()) ApiResponse.Success(pageResponse) else ApiResponse.Empty
        }
        emit(result)
    }
}
package com.android.developer.expert.core.data.source.local

import com.android.developer.expert.core.data.source.local.dao.SearchDao
import com.android.developer.expert.core.data.source.local.entity.search.SearchEntity
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val dao: SearchDao) {
    fun search(query: String) =
        if (query.isEmpty()) flowOf(listOf()) else dao.search(query)

    suspend fun insert(searchEntity: List<SearchEntity>) = dao.insert(searchEntity)
}
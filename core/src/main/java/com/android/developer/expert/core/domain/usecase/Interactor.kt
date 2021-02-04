package com.android.developer.expert.core.domain.usecase

import androidx.paging.PagingData
import com.android.developer.expert.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface Interactor {

    fun getDiscoverTv(): Flow<PagingData<ItemModel>>

    fun getTv(id: Int): Flow<Resource<DetailTvModel>>

    fun getMovie(id: Int): Flow<Resource<DetailMovieModel>>

    fun getDiscoverMovie(): Flow<PagingData<ItemModel>>

    fun getMovieFavorite(): Flow<PagingData<ItemModel>>

    fun getTvFavorite(): Flow<PagingData<ItemModel>>

    suspend fun setFavorite(type: Type<*>, isFavorite: Boolean): Int

    fun search(query: String): Flow<Resource<List<SearchModel>>>
}
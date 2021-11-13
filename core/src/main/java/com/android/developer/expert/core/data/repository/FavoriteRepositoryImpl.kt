package com.android.developer.expert.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.android.developer.expert.core.data.mapper.toItemModel
import com.android.developer.expert.core.data.source.local.MovieDataSource
import com.android.developer.expert.core.data.source.local.TvDataSource
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.model.Type
import com.android.developer.expert.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val movie: MovieDataSource,
    private val tv: TvDataSource
) : FavoriteRepository {
    private val config = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override fun getMovieFavorite(): Flow<PagingData<ItemModel>> = Pager(
        config = config, pagingSourceFactory = { movie.getFavorite() }
    ).flow.map { it.map { data -> data.toItemModel(data.title) } }

    override fun getTvFavorite(): Flow<PagingData<ItemModel>> = Pager(
        config = config,
        pagingSourceFactory = { tv.getFavorite() }
    ).flow.map { it.map { data -> data.toItemModel(data.name) } }

    override suspend fun setFavorite(type: Type<*>, isFavorite: Boolean) = when (type) {
        is Type.Movie -> movie.setFavorite(type.id, isFavorite)
        is Type.Tv -> tv.setFavorite(type.id, isFavorite)
    }
}
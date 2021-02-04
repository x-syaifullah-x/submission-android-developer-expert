package com.android.developer.expert.core.data.repository

import androidx.paging.*
import com.android.developer.expert.core.data.mapper.*
import com.android.developer.expert.core.data.source.local.MovieDataSource
import com.android.developer.expert.core.data.source.mediator.DiscoverMovieRemoteMediator
import com.android.developer.expert.core.data.source.mediator.networkBound
import com.android.developer.expert.core.data.source.remote.RemoteMovieDataSource
import com.android.developer.expert.core.domain.model.ItemModel
import com.android.developer.expert.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRepositoryImpl @Inject constructor(
    private val remoteMovie: RemoteMovieDataSource,
    private val movie: MovieDataSource,
    private val discoverMovieRemoteMediator: DiscoverMovieRemoteMediator
) : MovieRepository {

    private val config = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override fun getMovie(id: Int) = networkBound(
        loadFromDB = { movie.getMovie(id).map { it.toDetailMovieModel() } },
        fetch = { remoteMovie.getMovieWithMovieRecommendation(id) },
        saveFetchResult = {
            val movieEntity = it.toMovieEntity()
            val genres = it.genres.map { data -> data.toMovieGenreEntity() }
            val recommendations = it.recommendations.map { data -> data.toPageEmbedded() }
            movie.insert(movieEntity, genres, recommendations)
        }
    )

    override fun getDiscoverMovie(): Flow<PagingData<ItemModel>> = Pager(
        config = config,
        remoteMediator = discoverMovieRemoteMediator,
        pagingSourceFactory = { movie.getMovieResult() }
    ).flow.map {
        it.map { data -> data.toItemModel(data.title) }
    }
}
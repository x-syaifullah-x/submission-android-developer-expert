package com.android.developer.expert.core.data.source.local.dao

import androidx.room.Dao
import com.android.developer.expert.core.data.source.local.dao.discover.movie.DiscoverMovie
import com.android.developer.expert.core.data.source.local.dao.movie.Movie
import com.android.developer.expert.core.data.source.local.dao.movie.RecommendationMovie
import com.android.developer.expert.core.data.source.local.database.TheMovieDbDatabase

@Dao
abstract class MovieDao(val db: TheMovieDbDatabase) : DiscoverMovie, Movie, RecommendationMovie
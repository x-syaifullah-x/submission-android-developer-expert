package com.android.developer.expert.core.data.source.local.dao

import androidx.room.Dao
import com.android.developer.expert.core.data.source.local.dao.discover.tv.DiscoverTv
import com.android.developer.expert.core.data.source.local.dao.tv.RecommendationTv
import com.android.developer.expert.core.data.source.local.dao.tv.Tv
import com.android.developer.expert.core.data.source.local.database.TheMovieDbDatabase

@Dao
abstract class TvDao(val db: TheMovieDbDatabase) : DiscoverTv, Tv, RecommendationTv
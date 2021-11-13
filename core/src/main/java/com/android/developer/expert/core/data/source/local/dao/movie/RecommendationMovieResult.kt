package com.android.developer.expert.core.data.source.local.dao.movie

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.developer.expert.core.data.source.local.entity.recommendation.movie.RecommendationMovieResultEntity
import kotlinx.coroutines.flow.Flow

interface RecommendationMovieResult {
    @Query("SELECT * FROM ${RecommendationMovieResultEntity.TABLE_NAME}")
    fun getRecommendation(): Flow<List<RecommendationMovieResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendations(list: List<RecommendationMovieResultEntity>): List<Long>
}
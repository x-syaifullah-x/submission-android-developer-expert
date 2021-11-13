package com.android.developer.expert.core.data.source.local.dao.movie

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.developer.expert.core.data.source.local.entity.movie.MovieGenreEntity

interface MovieGenre {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieGenres(list: List<MovieGenreEntity>): List<Long>
}
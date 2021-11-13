package com.android.developer.expert.core.data.source.local.dao.discover.tv

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.developer.expert.core.data.source.local.entity.discover.tv.DiscoverTvResultEntity
import com.android.developer.expert.core.data.source.local.entity.discover.tv.DiscoverTvResultEntity.Companion.ID
import com.android.developer.expert.core.data.source.local.entity.discover.tv.DiscoverTvResultEntity.Companion.PRIMARY_KEY
import com.android.developer.expert.core.data.source.local.entity.discover.tv.DiscoverTvResultEntity.Companion.TABLE_NAME

interface DiscoverTvResult {
    @Query("SELECT * FROM $TABLE_NAME ORDER BY $PRIMARY_KEY ASC")
    fun getResult(): PagingSource<Int, DiscoverTvResultEntity>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearDiscoverMovieResult(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDiscoverResults(list: List<DiscoverTvResultEntity>): List<Long>

    @Query("SELECT EXISTS(SELECT * FROM $TABLE_NAME WHERE $ID = :id)")
    suspend fun isExist(id: Int): Boolean
}
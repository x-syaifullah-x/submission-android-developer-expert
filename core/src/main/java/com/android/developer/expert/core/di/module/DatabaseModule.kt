package com.android.developer.expert.core.di.module

import android.app.Application
import androidx.room.Room
import com.android.developer.expert.core.data.source.local.database.TheMovieDbDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, TheMovieDbDatabase::class.java, this::class.java.name)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(@Singleton database: TheMovieDbDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun provideTvDao(@Singleton database: TheMovieDbDatabase) = database.tvDao()

    @Singleton
    @Provides
    fun provideSearchDao(@Singleton database: TheMovieDbDatabase) = database.searchDao()
}
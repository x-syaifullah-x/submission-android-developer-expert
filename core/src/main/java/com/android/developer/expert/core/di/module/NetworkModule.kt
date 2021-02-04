package com.android.developer.expert.core.di.module

import com.android.developer.expert.core.BuildConfig
import com.android.developer.expert.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val TIME_OUT: Long = 5
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
}
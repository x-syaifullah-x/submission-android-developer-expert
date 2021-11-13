package com.android.developer.expert.core.di.module

import android.app.Application
import com.android.developer.expert.core.BuildConfig
import com.android.developer.expert.core.data.source.remote.network.ApiService
import com.android.developer.expert.core.data.source.remote.network.DnsResolver
import com.android.developer.expert.core.data.source.remote.network.SSLCertificateConfigurator.getSSLConfiguration
import com.android.developer.expert.core.data.source.remote.network.SSLCertificateConfigurator.getTrustManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Module
class NetworkModule {
    companion object {
        private const val TIME_OUT: Long = 5
    }

    @Singleton
    @Provides
    fun provideTrustManagers(application: Application): X509TrustManager {
        val trustManagers = getTrustManager(application).trustManagers
        return if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) throw IllegalStateException(
            "Unexpected default trust managers: ${Arrays.toString(trustManagers)}"
        ) else trustManagers[0] as X509TrustManager
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        application: Application,
        x509TrustManager: X509TrustManager
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return builder
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .dns(DnsResolver(builder.build()))
            .sslSocketFactory(getSSLConfiguration(application).socketFactory, x509TrustManager)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
}
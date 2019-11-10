package com.renanlukas.feature.core.data

import com.google.gson.GsonBuilder
import com.renanlukas.investmentsimulator.feature.core.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(GSON_CONVERTER_FACTORY)
    fun provideGsonConverterFactory(): Converter.Factory =
        GsonConverterFactory.create(GsonBuilder().apply {
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            setPrettyPrinting()
        }.create())

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: BaseUrl,
        okHttpClient: OkHttpClient,
        @Named(GSON_CONVERTER_FACTORY) gsonConverterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(baseUrl.value)
            client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(gsonConverterFactory)
        }.build()

    @Provides
    @Singleton
    fun provideBaseURL(): BaseUrl =
        BaseUrl(" https://api-simulator-calc.easynvest.com.br/")
}

private const val GSON_CONVERTER_FACTORY = "GSON_CONVERTER_FACTORY"
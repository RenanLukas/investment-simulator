package com.renanlukas.feature.core.di

import com.renanlukas.feature.core.data.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface CoreComponent {

    fun retrofit(): Retrofit
}
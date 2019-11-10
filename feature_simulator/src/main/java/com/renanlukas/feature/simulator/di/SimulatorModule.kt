package com.renanlukas.feature.simulator.di

import com.renanlukas.feature.simulator.data.SimulationService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SimulatorModule {

    @Provides
    @Singleton
    fun provideSimulationService(retrofit: Retrofit): SimulationService =
        retrofit.create(SimulationService::class.java)
}
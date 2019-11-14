package com.renanlukas.feature.simulator.di.create

import com.renanlukas.feature.simulator.data.SimulationService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SimulatorModule {

    @Provides
    fun provideSimulationService(retrofit: Retrofit): SimulationService =
        retrofit.create(SimulationService::class.java)
}
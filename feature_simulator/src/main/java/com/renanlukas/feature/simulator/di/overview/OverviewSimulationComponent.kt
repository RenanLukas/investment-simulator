package com.renanlukas.feature.simulator.di.overview

import com.renanlukas.feature.core.data.NetworkModule
import com.renanlukas.feature.core.di.CoreComponent
import com.renanlukas.feature.simulator.di.create.SimulatorModule
import com.renanlukas.feature.simulator.domain.Simulation
import dagger.BindsInstance
import dagger.Component



class OverviewSimulationComponent {

    @Component.Builder
    internal interface Builder {
        @BindsInstance
        fun userName(@UserName userName: String): Builder

        fun build(): OverviewSimulationComponent
    }
}
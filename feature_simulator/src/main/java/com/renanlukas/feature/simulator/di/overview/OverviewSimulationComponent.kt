package com.renanlukas.feature.simulator.di.overview

import com.renanlukas.feature.core.di.CoreComponent
import com.renanlukas.feature.simulator.domain.Simulation
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [CoreComponent::class])
internal interface OverviewSimulationComponent {

    @Component.Builder
    interface Builder {
        fun coreComponent(component: CoreComponent): Builder

        @BindsInstance
        fun simulation(simulation: Simulation): Builder

        fun build(): OverviewSimulationComponent
    }

    fun inject(overviewSimulationFragment: OverviewSimulationFragment)
}
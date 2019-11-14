package com.renanlukas.feature.simulator.di.create

import com.renanlukas.feature.core.data.NetworkModule
import com.renanlukas.feature.core.di.CoreComponent
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationFragment
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationFragment
import dagger.Component

@Component(
    modules = [SimulatorModule::class, NetworkModule::class],
    dependencies = [CoreComponent::class]
)
internal interface SimulatorComponent {
    fun inject(createSimulationFragment: CreateSimulationFragment)
}
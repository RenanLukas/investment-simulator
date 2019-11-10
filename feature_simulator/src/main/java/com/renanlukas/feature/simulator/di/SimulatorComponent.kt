package com.renanlukas.feature.simulator.di

import com.renanlukas.feature.core.di.CoreComponent
import com.renanlukas.feature.core.di.FeatureScope
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationFragment
import dagger.Component

@Component(modules = [SimulatorModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface SimulatorComponent {
    fun inject(createSimulationFragment: CreateSimulationFragment)
}
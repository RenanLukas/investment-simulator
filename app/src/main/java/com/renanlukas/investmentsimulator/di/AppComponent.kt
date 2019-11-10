package com.renanlukas.investmentsimulator.di

import com.renanlukas.feature.core.di.CoreComponent
import com.renanlukas.feature.core.di.FeatureScope
import com.renanlukas.investmentsimulator.presentation.HomeActivity
import dagger.Component

@Component(dependencies = [CoreComponent::class])
@FeatureScope
interface AppComponent {
    fun inject(homeActivity: HomeActivity)
}
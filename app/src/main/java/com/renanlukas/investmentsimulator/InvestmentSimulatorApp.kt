package com.renanlukas.investmentsimulator

import android.app.Application
import com.renanlukas.feature.core.di.CoreComponent
import com.renanlukas.feature.core.di.CoreComponentProvider
import com.renanlukas.feature.core.di.DaggerCoreComponent

class InvestmentSimulatorApp : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .build()
        }
        return coreComponent
    }
}
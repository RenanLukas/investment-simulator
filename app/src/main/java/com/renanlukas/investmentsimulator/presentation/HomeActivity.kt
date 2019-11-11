package com.renanlukas.investmentsimulator.presentation

import android.os.Bundle
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.BaseInjectionActivity
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationFragment
import com.renanlukas.investmentsimulator.R
import com.renanlukas.investmentsimulator.di.DaggerAppComponent

class HomeActivity : BaseInjectionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupContainer()
    }

    override fun inject() {
        DaggerAppComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .build()
            .inject(this)
    }

    private fun setupContainer() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CreateSimulationFragment.newInstance())
            .commit()
    }
}
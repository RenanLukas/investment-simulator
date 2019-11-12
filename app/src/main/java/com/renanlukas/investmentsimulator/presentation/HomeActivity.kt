package com.renanlukas.investmentsimulator.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.navigator.Navigator
import com.renanlukas.feature.core.presentation.BaseInjectionActivity
import com.renanlukas.feature.core.ui.into
import com.renanlukas.feature.core.ui.replace
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationFragment
import com.renanlukas.investmentsimulator.R
import com.renanlukas.investmentsimulator.di.DaggerAppComponent

class HomeActivity : BaseInjectionActivity(), Navigator {
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

    override fun navigate(fragment: Fragment) {
        this replace fragment into R.id.container
    }

    private fun setupContainer() {
        this replace CreateSimulationFragment.newInstance() into R.id.container
    }
}
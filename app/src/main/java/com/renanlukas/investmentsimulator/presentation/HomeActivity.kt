package com.renanlukas.investmentsimulator.presentation

import android.os.Bundle
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.DaggerActivity
import com.renanlukas.investmentsimulator.R
import com.renanlukas.investmentsimulator.di.DaggerAppComponent

class HomeActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun inject() {
        DaggerAppComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .build()
            .inject(this)
    }
}
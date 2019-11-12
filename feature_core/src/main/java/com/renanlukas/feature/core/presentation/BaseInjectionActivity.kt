package com.renanlukas.feature.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renanlukas.feature.core.navigator.Navigator

abstract class BaseInjectionActivity : AppCompatActivity(), Navigator {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }
}
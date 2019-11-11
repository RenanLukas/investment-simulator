package com.renanlukas.feature.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseInjectionActivity : AppCompatActivity() {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }
}
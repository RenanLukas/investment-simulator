package com.renanlukas.feature.core.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseInjectionFragment : Fragment() {

    abstract fun inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inject()
        super.onViewCreated(view, savedInstanceState)
    }
}
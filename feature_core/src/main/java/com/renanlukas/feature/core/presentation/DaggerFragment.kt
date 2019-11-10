package com.renanlukas.feature.core.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class DaggerFragment : Fragment() {

    abstract fun inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
    }
}
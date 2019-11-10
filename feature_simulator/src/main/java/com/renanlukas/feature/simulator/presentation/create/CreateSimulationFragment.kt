package com.renanlukas.feature.simulator.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.di.DaggerSimulatorComponent

class CreateSimulationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_create_simulation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
    }

    private fun inject() {
        activity?.let {
            DaggerSimulatorComponent
                .builder()
                .coreComponent(CoreInjectHelper.provideCoreComponent(it.applicationContext))
                .build()
                .inject(this)
        }
    }
}
package com.renanlukas.feature.simulator.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.DaggerFragment
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.data.SimulationService
import com.renanlukas.feature.simulator.di.DaggerSimulatorComponent
import javax.inject.Inject

class CreateSimulationFragment : DaggerFragment() {

    @Inject
    lateinit var createSimulationViewModelFactory: CreateSimulationViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, createSimulationViewModelFactory).get(CreateSimulationViewModel::class.java)
    }

    @Inject
    lateinit var simulationService: SimulationService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_create_simulation, container, false)

    override fun inject() {
        activity?.let {
            DaggerSimulatorComponent
                .builder()
                .coreComponent(CoreInjectHelper.provideCoreComponent(it.applicationContext))
                .build()
                .inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
    }

    private fun observeViewState() {
//        viewModel.viewState.observe(this, Observer { state ->
//            when(state) {
//
//            }
//        }
    }

    companion object {
        fun newInstance(): CreateSimulationFragment = CreateSimulationFragment()
    }
}
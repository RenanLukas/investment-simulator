package com.renanlukas.feature.simulator.presentation.overview

import androidx.lifecycle.Observer
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.BaseViewModelFragment
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.di.DaggerSimulatorComponent
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationViewState.Initial
import javax.inject.Inject

class OverviewSimulationFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: OverviewSimulationViewModelFactory

    override val viewModel by viewModel<OverviewSimulationViewModel> { viewModelFactory }

    override fun layoutResource() = R.layout.fragment_overview_simulation

    override fun inject() {
        activity?.let {
            DaggerSimulatorComponent
                .builder()
                .coreComponent(CoreInjectHelper.provideCoreComponent(it.applicationContext))
                .build()
                .inject(this)
        }
    }

    override fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is Initial -> buildInitial(state)
            }
        })
    }

    private fun buildInitial(state: Initial) {

    }

    companion object {
        @JvmStatic
        fun newInstance(): OverviewSimulationFragment = OverviewSimulationFragment()
    }
}
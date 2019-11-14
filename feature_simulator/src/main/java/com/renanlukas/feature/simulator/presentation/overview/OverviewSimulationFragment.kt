package com.renanlukas.feature.simulator.presentation.overview

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.BaseViewModelFragment
import com.renanlukas.feature.core.ui.MainButtonView
import com.renanlukas.feature.core.ui.colorText
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.di.create.DaggerSimulatorComponent
import com.renanlukas.feature.simulator.domain.Simulation
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationViewState.Initial
import kotlinx.android.synthetic.main.fragment_overview_simulation.*
import javax.inject.Inject

class OverviewSimulationFragment : BaseViewModelFragment() {

    private lateinit var resultsAdapter: OverviewSimulationAdapter

    @Inject
    lateinit var viewModelFactory: OverviewSimulationViewModelFactory

    override val viewModel by viewModel<OverviewSimulationViewModel> { viewModelFactory }

    override fun layoutResource() = R.layout.fragment_overview_simulation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO Inject on VM constructor
        arguments?.let { viewModel.injectSimulation(it.getParcelable(ARGUMENT_SIMULATION)) }
        setupSimulationResultsRecyclerView()
    }

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

    private fun setupSimulationResultsRecyclerView() {
        with(simulationResultRecyclerView) {
            resultsAdapter = OverviewSimulationAdapter()
            adapter = resultsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
    }

    private fun buildInitial(state: Initial) {
        with(state) {
            newSimulationButton.bind(MainButtonView.Entity(actionButtonLabel)) { viewModel.onNewSimulation() }
            overviewSimulationTitle.text = getString(simulationTitle)
            overviewSimulationValue.text = simulationValue
            overviewSimulationTotalProfit.colorText(
                fullText = "${getString(simulationTotalProfitTextDescription)}$simulationTotalProfitTextHighlight",
                textToColor = simulationTotalProfitTextHighlight,
                color = simulationTotalProfitColorHighlight
            )
            resultsAdapter.load(simulationDetails)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(simulation: Simulation): OverviewSimulationFragment =
            OverviewSimulationFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENT_SIMULATION, simulation)
                }
            }
    }
}

private const val ARGUMENT_SIMULATION = "ARGUMENT_SIMULATION"
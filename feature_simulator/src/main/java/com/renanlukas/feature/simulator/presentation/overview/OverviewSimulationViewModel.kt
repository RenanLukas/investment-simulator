package com.renanlukas.feature.simulator.presentation.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renanlukas.feature.core.presentation.BaseViewModel
import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.domain.Simulation
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationNavigationEvent
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationViewState.Initial
import javax.inject.Inject

class OverviewSimulationViewModel @Inject constructor(
    private val currencyFormat: CurrencyFormat
) : BaseViewModel() {

    private val mutableViewState: MutableLiveData<OverviewSimulationViewState> = MutableLiveData()
    val viewState: LiveData<OverviewSimulationViewState> = mutableViewState

    private lateinit var simulation: Simulation

    //TODO Inject on VM constructor
    fun injectSimulation(simulation: Simulation?) {
        if (simulation == null) {
            throw IllegalArgumentException("Simulation argument is mandatory")
        }
        this.simulation = simulation
    }

    override fun initialize() {
        val totalProfitHighlight = currencyFormat.format(simulation.grossAmountProfit)
        mutableViewState.postValue(
            Initial(
                simulationTitle = R.string.overview_simulation_title,
                simulationValue = currencyFormat.format(simulation.investmentParameter.investedAmount),
                simulationTotalProfitTextDescription = R.string.overview_simulation_total_profit,
                simulationTotalProfitTextHighlight = totalProfitHighlight,
                simulationTotalProfitColorHighlight = R.color.accent,
                simulationDetails = listOf(),
                actionButtonLabel = R.string.overview_simulation_action
            )
        )
    }

    fun onNewSimulation() = navigationSingleLiveEvent.postValue(CreateSimulationNavigationEvent)
}
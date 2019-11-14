package com.renanlukas.feature.simulator.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.simulator.domain.Simulation
import javax.inject.Inject

class OverviewSimulationViewModelFactory @Inject constructor(
    private val currencyFormat: CurrencyFormat,
    private val overviewSimulationDetailsMapper: OverviewSimulationDetailsMapper,
    private val simulation: Simulation
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OverviewSimulationViewModel::class.java)) {
            OverviewSimulationViewModel(
                currencyFormat,
                overviewSimulationDetailsMapper,
                simulation
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
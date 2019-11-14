package com.renanlukas.feature.simulator.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import javax.inject.Inject

class OverviewSimulationViewModelFactory @Inject constructor(
    private val currencyFormat: CurrencyFormat
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OverviewSimulationViewModel::class.java)) {
            OverviewSimulationViewModel(currencyFormat) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
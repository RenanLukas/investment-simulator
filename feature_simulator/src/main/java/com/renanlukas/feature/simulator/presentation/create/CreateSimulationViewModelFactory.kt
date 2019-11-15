package com.renanlukas.feature.simulator.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.core.presentation.format.DateFormat
import com.renanlukas.feature.core.presentation.format.PercentFormat
import com.renanlukas.feature.simulator.domain.GetSimulation
import javax.inject.Inject

class CreateSimulationViewModelFactory @Inject constructor(
    private val getSimulation: GetSimulation,
    private val currencyFormat: CurrencyFormat,
    private val percentFormat: PercentFormat,
    private val dateFormat: DateFormat
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CreateSimulationViewModel::class.java)) {
            CreateSimulationViewModel(getSimulation, currencyFormat, percentFormat, dateFormat) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
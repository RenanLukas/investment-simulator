package com.renanlukas.feature.simulator.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renanlukas.feature.simulator.domain.GetSimulation
import javax.inject.Inject

class CreateSimulationViewModelFactory @Inject constructor(
    private val getSimulation: GetSimulation
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CreateSimulationViewModel::class.java)) {
            CreateSimulationViewModel(getSimulation) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
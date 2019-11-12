package com.renanlukas.feature.simulator.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class OverviewSimulationViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OverviewSimulationViewModel::class.java)) {
            OverviewSimulationViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}
package com.renanlukas.feature.simulator.presentation.overview

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.renanlukas.feature.core.ui.DescriptionValueView

sealed class OverviewSimulationViewState {

    data class Initial(
        @StringRes val simulationTitle: Int,
        val simulationValue: String,
        @StringRes val simulationTotalProfitTextDescription: Int,
        val simulationTotalProfitTextHighlight: String,
        @ColorRes val simulationTotalProfitColorHighlight: Int,
        val simulationDetails: List<DescriptionValueView.Entity>,
        @StringRes val actionButtonLabel: Int
    ) : OverviewSimulationViewState()
}
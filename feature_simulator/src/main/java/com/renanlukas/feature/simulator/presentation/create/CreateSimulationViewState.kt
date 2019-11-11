package com.renanlukas.feature.simulator.presentation.create

import androidx.annotation.StringRes

sealed class CreateSimulationViewState {

    data class CreateSimulationInitial(
        @StringRes val amountTitle: Int,
        @StringRes val amountHint: Int,
        val amountMandatory: Boolean,
        @StringRes val maturityDateTitle: Int,
        @StringRes val maturityDateHint: Int,
        val maturityDateMandatory: Boolean,
        @StringRes val cdiPercentageTitle: Int,
        @StringRes val cdiPercentageHint: Int,
        val cdiPercentageMandatory: Boolean,
        @StringRes val actionButtonLabel: Int
    ) : CreateSimulationViewState()

    data class UpdateSimulation(val isSimulateEnabled: Boolean) : CreateSimulationViewState()

    object Loading : CreateSimulationViewState()

    object OverviewSimulation
}
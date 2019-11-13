package com.renanlukas.feature.simulator.presentation.create

import androidx.annotation.StringRes
import com.renanlukas.feature.core.ui.TitleInputView

sealed class CreateSimulationViewState {

    data class Initial(
        val amountViewEntity: TitleInputView.Entity,
        val maturityViewEntity: TitleInputView.Entity,
        val cdiViewEntity: TitleInputView.Entity,
        @StringRes val actionButtonLabel: Int,
        val enableAction: Boolean
    ) : CreateSimulationViewState()

    data class SimulationChanged(val enableAction: Boolean) : CreateSimulationViewState()

    object Loading : CreateSimulationViewState()

    data class Error(val message: String) : CreateSimulationViewState()
}
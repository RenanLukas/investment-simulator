package com.renanlukas.feature.simulator.presentation.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renanlukas.feature.core.presentation.BaseViewModel
import com.renanlukas.feature.core.rx.withIOScheduler
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.domain.GetSimulation
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationViewState.CreateSimulationInitial
import javax.inject.Inject

class CreateSimulationViewModel @Inject constructor(
    private val getSimulation: GetSimulation
) : BaseViewModel() {

    private val mutableViewState: MutableLiveData<CreateSimulationViewState> = MutableLiveData()
    val viewState: LiveData<CreateSimulationViewState> = mutableViewState

    override fun initialize() {
        mutableViewState.postValue(
            CreateSimulationInitial(
                amountHint = R.string.simulator_investment_amount_hint,
                amountTitle = R.string.simulator_investment_amount_title,
                amountMandatory = true,
                maturityDateTitle = R.string.simulator_investment_maturity_date_title,
                maturityDateHint = R.string.simulator_investment_maturity_date_hint,
                maturityDateMandatory = true,
                cdiPercentageTitle = R.string.simulator_investment_cdi_percentage_title,
                cdiPercentageHint = R.string.simulator_investment_cdi_percentage_hint,
                cdiPercentageMandatory = true,
                actionButtonLabel = R.string.simulator_investment_action
            )
        )
    }

    fun onSimulate() = getSimulation()

    private fun getSimulation() =
        getSimulation.execute(
            123.45, 50, "2020-02-02"
        ).withIOScheduler()
            .subscribe({
                val a = 0
            }, {
                val a = 0
            })
}
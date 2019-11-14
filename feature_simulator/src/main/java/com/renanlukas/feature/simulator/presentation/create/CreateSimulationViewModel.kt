package com.renanlukas.feature.simulator.presentation.create

import android.text.InputType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renanlukas.feature.core.presentation.BaseViewModel
import com.renanlukas.feature.core.rx.withIOScheduler
import com.renanlukas.feature.core.ui.TitleInputView
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.domain.GetSimulation
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationViewState.*
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationNavigationEvent
import javax.inject.Inject

class CreateSimulationViewModel @Inject constructor(
    private val getSimulation: GetSimulation
) : BaseViewModel() {

    private val mutableViewState: MutableLiveData<CreateSimulationViewState> = MutableLiveData()
    val viewState: LiveData<CreateSimulationViewState> = mutableViewState

    override fun initialize() {
        mutableViewState.postValue(
            Initial(
                amountViewEntity = TitleInputView.Entity(
                    titleValue = R.string.simulator_investment_amount_title,
                    hintValue = R.string.simulator_investment_amount_hint,
                    inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL,
                    isMandatory = true
                ),
                maturityViewEntity = TitleInputView.Entity(
                    titleValue = R.string.simulator_investment_maturity_date_title,
                    hintValue = R.string.simulator_investment_maturity_date_hint,
                    inputType = InputType.TYPE_CLASS_NUMBER,
                    isMandatory = true
                ),
                cdiViewEntity = TitleInputView.Entity(
                    titleValue = R.string.simulator_investment_cdi_percentage_title,
                    hintValue = R.string.simulator_investment_cdi_percentage_hint,
                    inputType = InputType.TYPE_CLASS_NUMBER,
                    isMandatory = true
                ),
                actionButtonLabel = R.string.simulator_investment_action,
                enableAction = true
            )
        )
    }

    fun onSimulate() = getSimulation()

    private fun getSimulation() =
        getSimulation.execute(
            123.45, 50, "2020-02-02"
        ).withIOScheduler()
            .doOnSubscribe { mutableViewState.postValue(Loading) }
            .subscribe({
                navigationSingleLiveEvent.postValue(OverviewSimulationNavigationEvent(it))
            }, {
                mutableViewState.postValue(Error(R.string.simulator_investment_error))
            })
}
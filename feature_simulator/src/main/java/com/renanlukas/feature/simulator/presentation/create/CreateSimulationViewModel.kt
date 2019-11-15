package com.renanlukas.feature.simulator.presentation.create

import android.text.InputType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renanlukas.feature.core.presentation.BaseViewModel
import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.core.presentation.format.DateFormat
import com.renanlukas.feature.core.presentation.format.PercentFormat
import com.renanlukas.feature.core.rx.withIOScheduler
import com.renanlukas.feature.core.ui.Text
import com.renanlukas.feature.core.ui.TitleInputView
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.domain.GetSimulation
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationViewState.*
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationNavigationEvent
import javax.inject.Inject

class CreateSimulationViewModel @Inject constructor(
    private val getSimulation: GetSimulation,
    private val currencyFormat: CurrencyFormat,
    private val dateFormat: DateFormat,
    private val percentFormat: PercentFormat
) : BaseViewModel() {

    private val mutableViewState: MutableLiveData<CreateSimulationViewState> = MutableLiveData()
    val viewState: LiveData<CreateSimulationViewState> = mutableViewState

    private val mutableAmountField: MutableLiveData<String> = MutableLiveData()
    val amountField: LiveData<String> = mutableAmountField

    private val mutableMaturityDateField: MutableLiveData<String> =
        MutableLiveData()
    val maturityDateField: LiveData<String> = mutableMaturityDateField

    private val mutableCdiInvestmentField: MutableLiveData<String> =
        MutableLiveData()
    val cdiInvestmentField: LiveData<String> = mutableCdiInvestmentField

    override fun initialize() {
        mutableViewState.postValue(
            Initial(
                amountViewEntity = TitleInputView.Entity(
                    titleValue = Text.Resource(R.string.simulator_investment_amount_title),
                    hintValue = Text.StringLiteral(currencyFormat.currencyCode()),
                    inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL,
                    isMandatory = true
                ),
                maturityViewEntity = TitleInputView.Entity(
                    titleValue = Text.Resource(R.string.simulator_investment_maturity_date_title),
                    hintValue = Text.Resource(R.string.simulator_investment_maturity_date_hint),
                    inputType = InputType.TYPE_CLASS_NUMBER,
                    isMandatory = true
                ),
                cdiViewEntity = TitleInputView.Entity(
                    titleValue = Text.Resource(R.string.simulator_investment_cdi_percentage_title),
                    hintValue = Text.Resource(R.string.simulator_investment_cdi_percentage_hint),
                    inputType = InputType.TYPE_CLASS_NUMBER,
                    isMandatory = true
                ),
                actionButtonLabel = R.string.simulator_investment_action,
                enableAction = false
            )
        )
    }

    fun onSimulate(amount: String, maturityDate: String, cdiInvestment: String) =
        getSimulation.execute(
            amount.toDouble(),
            maturityDate.replace("/", "-"),
            cdiInvestment.toInt()
        ).withIOScheduler()
            .doOnSubscribe { mutableViewState.postValue(Loading) }
            .subscribe({
                navigationSingleLiveEvent.postValue(OverviewSimulationNavigationEvent(it))
            }, {
                mutableViewState.postValue(Error(R.string.simulator_investment_error))
            })

    fun onAmountChanged(rawAmount: String, rawMaturityDate: String, rawCdiInvestment: String) {
        with(rawAmount) {
            val cleanAmount = replace(Regex("[^\\d.,]"), "")
            val value =
                if (cleanAmount.isEmpty()) "" else currencyFormat.format(cleanAmount.toBigDecimal())
            mutableAmountField.postValue(value)
        }
        mutableViewState.postValue(
            SimulationChanged(
                shouldEnableActionButton(
                    rawAmount, rawMaturityDate, rawCdiInvestment
                )
            )
        )
    }

    fun onMaturityDateChanged(
        rawMaturityDate: String,
        rawAmount: String,
        rawCdiInvestment: String
    ) {
        with(rawMaturityDate) {
            val cleanAmount = replace("[^\\d/]", "")
            //TODO
//            mutableAmountField.postValue(dateFormat.format()
        }
        mutableViewState.postValue(
            SimulationChanged(
                shouldEnableActionButton(
                    rawAmount, rawMaturityDate, rawCdiInvestment
                )
            )
        )
    }

    fun onCdiInvestmentChanged(
        rawCdiInvestment: String,
        rawAmount: String,
        rawMaturityDate: String
    ) {
        with(rawCdiInvestment) {
            val cleanAmount = replace(Regex("[^\\d]"), "")
            val value =
                if (cleanAmount.isEmpty()) "" else percentFormat.format(cleanAmount.toBigDecimal())
            mutableAmountField.postValue(value)
        }
        mutableViewState.postValue(
            SimulationChanged(
                shouldEnableActionButton(
                    rawAmount, rawMaturityDate, rawCdiInvestment
                )
            )
        )
    }

    private fun shouldEnableActionButton(
        rawAmount: String,
        rawMaturityDate: String,
        rawCdiInvestment: String
    ) = rawAmount.isNotEmpty() && rawMaturityDate.isNotEmpty() && rawCdiInvestment.isNotEmpty()
}
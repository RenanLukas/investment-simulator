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
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class CreateSimulationViewModel @Inject constructor(
    private val getSimulation: GetSimulation,
    private val currencyFormat: CurrencyFormat,
    private val percentFormat: PercentFormat,
    private val dateFormat: DateFormat
) : BaseViewModel() {

    private val mutableViewState: MutableLiveData<CreateSimulationViewState> = MutableLiveData()
    val viewState: LiveData<CreateSimulationViewState> = mutableViewState

    private val mutableAmountField: MutableLiveData<FieldState> = MutableLiveData()
    val amountField: LiveData<FieldState> = mutableAmountField

    private val mutableMaturityDateField: MutableLiveData<FieldState> =
        MutableLiveData()
    val maturityDateField: LiveData<FieldState> = mutableMaturityDateField

    private val mutableCdiInvestmentField: MutableLiveData<FieldState> =
        MutableLiveData()
    val cdiInvestmentField: LiveData<FieldState> = mutableCdiInvestmentField

    override fun initialize() {
        mutableViewState.postValue(
            Initial(
                amountViewEntity = TitleInputView.Entity(
                    titleValue = Text.Resource(R.string.simulator_investment_amount_title),
                    hintValue = Text.StringLiteral(currencyFormat.currencyCode()),
                    inputType = InputType.TYPE_CLASS_NUMBER,
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

    fun onSimulate(amount: String, maturityDate: String, cdiInvestment: String) {
        val cleanAmount = amount
            .replace(Regex("[^\\d]"), "")
            .toDouble() / 100
        val cleanMaturityDate = maturityDate
            .replace(Regex("[^\\d/]"), "")
            .replace("/", "-")
        val cleanCdiInvestment = cdiInvestment
            .replace(Regex("[^\\d]"), "")
            .toInt()

        compositeDisposable.add(getSimulation.execute(
            cleanAmount,
            cleanMaturityDate,
            cleanCdiInvestment
        )
            .withIOScheduler()
            .doOnSubscribe { mutableViewState.postValue(Loading) }
            .subscribe({
                navigationSingleLiveEvent.postValue(OverviewSimulationNavigationEvent(it))
            }, {
                mutableViewState.postValue(Error(R.string.simulator_investment_error))
            })
        )
    }

    fun onAmountChanged(
        rawAmount: String, rawMaturityDate: String, rawCdiInvestment: String, isDeleting: Boolean
    ) {
        val cleanAmountString = rawAmount.replace(Regex("[^\\d]"), "")
        with(cleanAmountString) {
            val value = if (isEmpty()) "" else currencyFormat.format(toBigDecimal())
            mutableAmountField.postValue(
                FieldState(
                    value = value,
                    showError = false
                )
            )
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
        rawMaturityDate: String, rawAmount: String, rawCdiInvestment: String
    ) {
        val cleanAmountString = rawMaturityDate.replace(Regex("[^\\d]"), "")
        val maturityDateStringBuilder = StringBuilder(cleanAmountString).apply {
            val lastIndex = length - 1
            if (length >= 2 && lastIndex != 1) {
                insert(2, "/")
            }
            if (length >= 5 && lastIndex != 3) {
                insert(5, "/")
            }
        }.toString().take(10)

        with(maturityDateStringBuilder) {
            mutableMaturityDateField.postValue(
                FieldState(
                    value = this,
                    showError = this.isNotEmpty() && !dateFormat.isValid(
                        this,
                        DateFormat.Pattern.DayMonthYearSlash
                    )
                )
            )
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
        rawCdiInvestment: String, rawAmount: String, rawMaturityDate: String, isDeleting: Boolean
    ) {
        val amountAfterDeleting = with(rawCdiInvestment) { if (isDeleting) dropLast(1) else this }
        val cleanAmountString = amountAfterDeleting.replace(Regex("[^\\d]"), "")
        val value = if (cleanAmountString.isNotEmpty()) {
            val cleanAmount = cleanAmountString.toBigDecimal()
            if (cleanAmount > BigDecimal(100)) {
                percentFormat.format(cleanAmount.divide(BigDecimal(10), RoundingMode.DOWN))
            } else {
                percentFormat.format(cleanAmount)
            }
        } else ""
        mutableCdiInvestmentField.postValue(
            FieldState(
                value = value,
                showError = false
            )
        )
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
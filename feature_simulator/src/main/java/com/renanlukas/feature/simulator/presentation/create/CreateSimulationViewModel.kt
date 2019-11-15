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
            cleanAmount, cleanMaturityDate, cleanCdiInvestment
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

    //TODO [RenanLukas] Extract to separate class
    fun onAmountChanged(
        rawAmount: String, rawMaturityDate: String, rawCdiInvestment: String, isDeleting: Boolean
    ) {
        val amountWithoutDecimalPlaces = with(rawAmount) {
            if (isDeleting && isNotBlank()) {
                substring(0, rawAmount.indexOf(",0"))
            } else {
                replace(",00", "")
            }
        }

        val cleanAmountString = with(amountWithoutDecimalPlaces) {
            val clean = replace(Regex("[^\\d]"), "")
            if (isDeleting) clean.dropLast(1) else clean
        }
        val value = when {
            cleanAmountString.isBlank() -> ""
            else -> {
                val cleanAmount = cleanAmountString.toBigDecimal()
                if (cleanAmount > BigDecimal(MAX_VALUE_APP_INVESTMENT)) {
                    currencyFormat.format(cleanAmount.divide(BigDecimal(10)))
                } else {
                    currencyFormat.format(cleanAmount)
                }
            }
        }

        mutableAmountField.postValue(
            FieldState(
                value = value,
                showError = false
            )
        )
        postEnableActionButtonStatus(value, rawMaturityDate, rawCdiInvestment)
    }

    //TODO [RenanLukas] Extract to separate class
    fun onMaturityDateChanged(
        rawMaturityDate: String, rawAmount: String, rawCdiInvestment: String
    ) {
        val cleanMaturityDateString = rawMaturityDate.replace(Regex("[^\\d]"), "")
        val value = buildMaturityDateString(cleanMaturityDateString)

        with(value) {
            mutableMaturityDateField.postValue(
                FieldState(
                    value = this,
                    showError = this.isNotBlank() && !dateFormat.isInFuture(
                        this,
                        DateFormat.Pattern.DayMonthYearSlash
                    )
                )
            )
        }
        postEnableActionButtonStatus(
            rawAmount,
            value,
            rawCdiInvestment
        )
    }

    //TODO [RenanLukas] Extract to separate class
    fun onCdiInvestmentChanged(
        rawCdiInvestment: String, rawAmount: String, rawMaturityDate: String, isDeleting: Boolean
    ) {
        val cdiInvestmentAfterDeleting =
            with(rawCdiInvestment) { if (isDeleting) dropLast(1) else this }
        val cleanCdiInvestmentString = cdiInvestmentAfterDeleting.replace(Regex("[^\\d]"), "")
        val value = if (cleanCdiInvestmentString.isNotBlank()) {
            val cleanCdiInvestment = cleanCdiInvestmentString.toBigDecimal()
            if (cleanCdiInvestment > BigDecimal(100)) {
                percentFormat.format(cleanCdiInvestment.divide(BigDecimal(10), RoundingMode.DOWN))
            } else {
                percentFormat.format(cleanCdiInvestment)
            }
        } else ""
        mutableCdiInvestmentField.postValue(
            FieldState(
                value = value,
                showError = false
            )
        )
        postEnableActionButtonStatus(rawAmount, rawMaturityDate, value)
    }

    private fun buildMaturityDateString(cleanMaturityDateString: String): String {
        return StringBuilder(cleanMaturityDateString).apply {
            val lastIndex = length - 1
            if (length >= 2 && lastIndex != 1) {
                insert(2, "/")
            }
            if (length >= 5 && lastIndex != 3) {
                insert(5, "/")
            }
        }.toString().take(10)
    }

    private fun postEnableActionButtonStatus(
        rawAmount: String,
        rawMaturityDate: String,
        rawCdiInvestment: String
    ) = mutableViewState.postValue(
        SimulationChanged(
            rawAmount.isNotBlank() && rawMaturityDate.isNotBlank() && rawCdiInvestment.isNotBlank()
        )
    )

    companion object {
        internal const val MAX_VALUE_APP_INVESTMENT = 99999999
    }
}
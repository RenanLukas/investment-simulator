package com.renanlukas.feature.simulator.data

import java.math.BigDecimal
import java.util.*

data class SimulationRaw(
    val investmentParameter: InvestmentParameterRaw?,
    val grossAmount: Double?,
    val taxesAmount: Double?,
    val netAmount: Double?,
    val grossAmountProfit: Double?,
    val netAmountProfit: Double?,
    val annualGrossRateProfit: Double?,
    val monthlyGrossRateProfit: Double?,
    val dailyGrossRateProfit: BigDecimal?,
    val taxesRate: Double?,
    val rateProfit: Double?,
    val annualNetRateProfit: Double?
)

data class InvestmentParameterRaw(
    val investedAmount: Double?,
    val yearlyInterestRate: Double?,
    val maturityTotalDays: Int?,
    val maturityBusinessDays: Int?,
    val maturityDate: Date?,
    val rate: Double?,
    val isTaxFree: Boolean?
)

package com.renanlukas.feature.simulator.data

import java.math.BigDecimal
import java.util.*

data class SimulationRaw(
    val investmentParameter: InvestmentParameterRaw?,
    val grossAmount: BigDecimal?,
    val taxesAmount: BigDecimal?,
    val netAmount: BigDecimal?,
    val grossAmountProfit: BigDecimal?,
    val netAmountProfit: BigDecimal?,
    val annualGrossRateProfit: BigDecimal?,
    val monthlyGrossRateProfit: BigDecimal?,
    val dailyGrossRateProfit: BigDecimal?,
    val taxesRate: BigDecimal?,
    val rateProfit: BigDecimal?,
    val annualNetRateProfit: BigDecimal?
)

data class InvestmentParameterRaw(
    val investedAmount: BigDecimal?,
    val yearlyInterestRate: BigDecimal?,
    val maturityTotalDays: Int?,
    val maturityBusinessDays: Int?,
    val maturityDate: Date?,
    val rate: BigDecimal?,
    val isTaxFree: Boolean?
)

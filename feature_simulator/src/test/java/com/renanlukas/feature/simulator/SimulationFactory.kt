package com.renanlukas.feature.simulator

import com.renanlukas.feature.simulator.data.InvestmentParameterRaw
import com.renanlukas.feature.simulator.data.SimulationRaw
import com.renanlukas.feature.simulator.domain.InvestmentParameter
import com.renanlukas.feature.simulator.domain.Simulation
import java.math.BigDecimal
import java.util.*


fun createSimulation(
    investmentParameter: InvestmentParameter = createInvestmentParameter(),
    grossAmount: BigDecimal = BigDecimal(123.45),
    taxesAmount: BigDecimal = BigDecimal(123.45),
    netAmount: BigDecimal = BigDecimal(123.45),
    grossAmountProfit: BigDecimal = BigDecimal(123.45),
    netAmountProfit: BigDecimal = BigDecimal(123.45),
    annualGrossRateProfit: BigDecimal = BigDecimal(123.45),
    monthlyGrossRateProfit: BigDecimal = BigDecimal(123.45),
    dailyGrossRateProfit: BigDecimal = BigDecimal(123.45),
    taxesRate: BigDecimal = BigDecimal(123.45),
    rateProfit: BigDecimal = BigDecimal(123.45),
    annualNetRateProfit: BigDecimal = BigDecimal(123.45)
): Simulation =
    Simulation(
        investmentParameter = investmentParameter,
        grossAmount = grossAmount,
        taxesAmount = taxesAmount,
        netAmount = netAmount,
        grossAmountProfit = grossAmountProfit,
        netAmountProfit = netAmountProfit,
        annualGrossRateProfit = annualGrossRateProfit,
        monthlyGrossRateProfit = monthlyGrossRateProfit,
        dailyGrossRateProfit = dailyGrossRateProfit,
        taxesRate = taxesRate,
        rateProfit = rateProfit,
        annualNetRateProfit = annualNetRateProfit
    )

fun createInvestmentParameter(
    investedAmount: BigDecimal = BigDecimal(123.45),
    yearlyInterestRate: BigDecimal = BigDecimal(123.45),
    maturityTotalDays: Int = 10,
    maturityBusinessDays: Int = 10,
    maturityDate: Date = Date(1573753952000L),
    rate: BigDecimal = BigDecimal(123.45),
    isTaxFree: Boolean = false
): InvestmentParameter =
    InvestmentParameter(
        investedAmount = investedAmount,
        yearlyInterestRate = yearlyInterestRate,
        maturityTotalDays = maturityTotalDays,
        maturityBusinessDays = maturityBusinessDays,
        maturityDate = maturityDate,
        rate = rate,
        isTaxFree = isTaxFree
    )

fun createSimulationRaw(
    investmentParameter: InvestmentParameterRaw = createInvestmentParameterRaw(),
    grossAmount: BigDecimal = BigDecimal(123.45),
    taxesAmount: BigDecimal = BigDecimal(123.45),
    netAmount: BigDecimal = BigDecimal(123.45),
    grossAmountProfit: BigDecimal = BigDecimal(123.45),
    netAmountProfit: BigDecimal = BigDecimal(123.45),
    annualGrossRateProfit: BigDecimal = BigDecimal(123.45),
    monthlyGrossRateProfit: BigDecimal = BigDecimal(123.45),
    dailyGrossRateProfit: BigDecimal = BigDecimal(123.45),
    taxesRate: BigDecimal = BigDecimal(123.45),
    rateProfit: BigDecimal = BigDecimal(123.45),
    annualNetRateProfit: BigDecimal = BigDecimal(123.45)
): SimulationRaw =
    SimulationRaw(
        investmentParameter = investmentParameter,
        grossAmount = grossAmount,
        taxesAmount = taxesAmount,
        netAmount = netAmount,
        grossAmountProfit = grossAmountProfit,
        netAmountProfit = netAmountProfit,
        annualGrossRateProfit = annualGrossRateProfit,
        monthlyGrossRateProfit = monthlyGrossRateProfit,
        dailyGrossRateProfit = dailyGrossRateProfit,
        taxesRate = taxesRate,
        rateProfit = rateProfit,
        annualNetRateProfit = annualNetRateProfit
    )

fun createInvestmentParameterRaw(
    investedAmount: BigDecimal = BigDecimal(123.45),
    yearlyInterestRate: BigDecimal = BigDecimal(123.45),
    maturityTotalDays: Int = 10,
    maturityBusinessDays: Int = 10,
    maturityDate: Date = Date(1573753952000L),
    rate: BigDecimal = BigDecimal(123.45),
    isTaxFree: Boolean = false
): InvestmentParameterRaw =
    InvestmentParameterRaw(
        investedAmount = investedAmount,
        yearlyInterestRate = yearlyInterestRate,
        maturityTotalDays = maturityTotalDays,
        maturityBusinessDays = maturityBusinessDays,
        maturityDate = maturityDate,
        rate = rate,
        isTaxFree = isTaxFree
    )
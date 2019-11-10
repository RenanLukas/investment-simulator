package com.renanlukas.feature.simulator.domain

import com.renanlukas.feature.core.domain.getOrDefault
import com.renanlukas.feature.core.domain.getOrThrow
import com.renanlukas.feature.simulator.data.InvestmentParameterRaw
import com.renanlukas.feature.simulator.data.SimulationRaw
import javax.inject.Inject

class SimulationMapper @Inject constructor() {

    infix fun map(raw: SimulationRaw): Simulation =
        with(raw) {
            Simulation(
                investmentParameter = mapInvestmentParameter(investmentParameter.getOrThrow()),
                grossAmount = grossAmount.getOrThrow(),
                taxesAmount = taxesAmount.getOrThrow(),
                netAmount = netAmount.getOrThrow(),
                grossAmountProfit = grossAmountProfit.getOrThrow(),
                netAmountProfit = netAmountProfit.getOrThrow(),
                annualGrossRateProfit = annualGrossRateProfit.getOrThrow(),
                monthlyGrossRateProfit = monthlyGrossRateProfit.getOrThrow(),
                dailyGrossRateProfit = dailyGrossRateProfit.getOrThrow(),
                taxesRate = taxesRate.getOrThrow(),
                rateProfit = rateProfit.getOrThrow(),
                annualNetRateProfit = annualNetRateProfit.getOrThrow()
            )
        }

    private fun mapInvestmentParameter(raw: InvestmentParameterRaw): InvestmentParameter =
        with(raw) {
            InvestmentParameter(
                investedAmount = investedAmount.getOrThrow(),
                yearlyInterestRate = yearlyInterestRate.getOrThrow(),
                maturityTotalDays = maturityTotalDays.getOrDefault(-1),
                maturityBusinessDays = maturityBusinessDays.getOrDefault(-1),
                maturityDate = maturityDate.getOrThrow(),
                rate = rate.getOrThrow(),
                isTaxFree = isTaxFree.getOrThrow()
            )
        }
}
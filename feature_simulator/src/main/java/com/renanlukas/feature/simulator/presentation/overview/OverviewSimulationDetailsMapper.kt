package com.renanlukas.feature.simulator.presentation.overview

import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.core.presentation.format.DateFormat
import com.renanlukas.feature.core.presentation.format.PercentFormat
import com.renanlukas.feature.core.ui.DescriptionValueView
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.domain.Simulation
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class OverviewSimulationDetailsMapper @Inject constructor(
    private val currencyFormat: CurrencyFormat,
    private val dateFormat: DateFormat,
    private val percentFormat: PercentFormat
) {

    fun map(simulation: Simulation): List<DescriptionValueView.Entity> =
        listOf(
            mapInitialAmount(simulation.investmentParameter.investedAmount),
            mapGrossAmount(simulation.grossAmount),
            mapProfitAmount(simulation.grossAmountProfit),
            mapIncomeTaxAmount(simulation.taxesAmount),
            mapNetProfitAmount(simulation.netAmountProfit),
            mapMaturityDate(simulation.investmentParameter.maturityDate),
            mapMaturityTotalDays(simulation.investmentParameter.maturityTotalDays),
            mapMonthlyGrossRateProfit(simulation.monthlyGrossRateProfit),
            mapInvestmentRate(simulation.investmentParameter.rate),
            mapAnnualGrossRateProfit(simulation.annualGrossRateProfit),
            mapRateProfit(simulation.rateProfit)
        )

    private fun mapInitialAmount(investedAmount: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_initial_amount,
            value = currencyFormat.format(investedAmount)
        )

    private fun mapGrossAmount(grossAmount: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_gross_amount,
            value = currencyFormat.format(grossAmount)
        )

    private fun mapProfitAmount(grossAmountProfit: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_profit_amount,
            value = currencyFormat.format(grossAmountProfit)
        )

    private fun mapIncomeTaxAmount(taxesAmount: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_income_tax_amount,
            value = currencyFormat.format(taxesAmount)
        )

    private fun mapNetProfitAmount(netAmountProfit: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_net_profit_amount,
            value = currencyFormat.format(netAmountProfit)
        )

    private fun mapMaturityDate(maturityDate: Date): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_maturity_date,
            value = dateFormat.format(maturityDate.time, DateFormat.Pattern.DD_MM_YYYY)
        )

    private fun mapMaturityTotalDays(maturityTotalDays: Int): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_maturity_total_days,
            value = "$maturityTotalDays"
        )

    private fun mapMonthlyGrossRateProfit(monthlyGrossRateProfit: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_monthly_gross_rate_profit,
            value = currencyFormat.format(monthlyGrossRateProfit)
        )

    private fun mapInvestmentRate(rate: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_cdi_percentage,
            value = percentFormat.format(rate)
        )

    private fun mapAnnualGrossRateProfit(annualGrossRateProfit: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_annual_gross_rate_profit,
            value = currencyFormat.format(annualGrossRateProfit)
        )

    private fun mapRateProfit(rateProfit: BigDecimal): DescriptionValueView.Entity =
        DescriptionValueView.Entity(
            description = R.string.overview_simulation_rate_profit,
            value = currencyFormat.format(rateProfit)
        )
}
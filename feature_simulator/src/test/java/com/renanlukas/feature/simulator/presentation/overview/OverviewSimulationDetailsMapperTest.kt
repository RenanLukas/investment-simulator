package com.renanlukas.feature.simulator.presentation.overview

import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.core.presentation.format.DateFormat
import com.renanlukas.feature.core.presentation.format.PercentFormat
import com.renanlukas.feature.core.ui.DescriptionValueView
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.createSimulation
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OverviewSimulationDetailsMapperTest {

    private val currencyFormat: CurrencyFormat = mockk()
    private val dateFormat: DateFormat = mockk()
    private val percentFormat: PercentFormat = mockk()
    private val classToTest =
        OverviewSimulationDetailsMapper(currencyFormat, dateFormat, percentFormat)

    @Before
    fun setup() {
        every { currencyFormat.format(any()) } returns "R$ 123,45"
        every { dateFormat.format(any(), any()) } returns "19/11/2019"
        every { percentFormat.format(any()) } returns "5%"
    }

    @Test
    fun `should map simulation to list of overview details`() {
        val testSimulation = createSimulation()
        val expected = classToTest.map(testSimulation)

        val value = "R$ 123,45"

        val initialAmount = DescriptionValueView.Entity(
            description = R.string.overview_simulation_initial_amount,
            value = value
        )

        val grossAmount = DescriptionValueView.Entity(
            description = R.string.overview_simulation_gross_amount,
            value = value
        )

        val profitAmount = DescriptionValueView.Entity(
            description = R.string.overview_simulation_profit_amount,
            value = value
        )

        val incomeTaxAmount = DescriptionValueView.Entity(
            description = R.string.overview_simulation_income_tax_amount,
            value = value
        )

        val netProfitAmount = DescriptionValueView.Entity(
            description = R.string.overview_simulation_net_profit_amount,
            value = value
        )

        val maturityDate = DescriptionValueView.Entity(
            description = R.string.overview_simulation_maturity_date,
            value = "19/11/2019"
        )

        val maturityTotalDays = DescriptionValueView.Entity(
            description = R.string.overview_simulation_maturity_total_days,
            value = "10"
        )

        val monthlyGrossRateProfit = DescriptionValueView.Entity(
            description = R.string.overview_simulation_monthly_gross_rate_profit,
            value = value
        )

        val investmentRate = DescriptionValueView.Entity(
            description = R.string.overview_simulation_cdi_percentage,
            value = "5%"
        )

        val annualGrossRateProfit = DescriptionValueView.Entity(
            description = R.string.overview_simulation_annual_gross_rate_profit,
            value = value
        )

        val rateProfit = DescriptionValueView.Entity(
            description = R.string.overview_simulation_rate_profit,
            value = value
        )

        val actual = listOf(
            initialAmount,
            grossAmount,
            profitAmount,
            incomeTaxAmount,
            netProfitAmount,
            maturityDate,
            maturityTotalDays,
            monthlyGrossRateProfit,
            investmentRate,
            annualGrossRateProfit,
            rateProfit
        )
        assertEquals(expected, actual)
    }
}
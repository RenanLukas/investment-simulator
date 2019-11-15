package com.renanlukas.feature.simulator.presentation.overview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.renanlukas.feature.core.navigator.NavigationEvent
import com.renanlukas.feature.core.presentation.format.CurrencyFormat
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.createInvestmentParameter
import com.renanlukas.feature.simulator.createSimulation
import com.renanlukas.feature.simulator.domain.Simulation
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationNavigationEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal

class OverviewSimulationViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val currencyFormat: CurrencyFormat = mockk()
    private val overviewSimulationDetailsMapper: OverviewSimulationDetailsMapper = mockk()
    private val simulation: Simulation = createSimulation()

    private val viewStateObserver: Observer<OverviewSimulationViewState> = mockk()
    private val navigationObserver: Observer<NavigationEvent> = mockk()

    private val classToTest = OverviewSimulationViewModel(
        currencyFormat, overviewSimulationDetailsMapper, simulation
    )

    @Before
    fun setup() {
        classToTest.viewState.observeForever(viewStateObserver)
        classToTest.navigationSingleLiveEvent.observeForever(navigationObserver)

        every { viewStateObserver.onChanged(any()) } answers {}
        every { navigationObserver.onChanged(any()) } answers {}
    }

    @Test
    fun `should post Initial state on initialize`() {
        val testSimulation = createSimulation()
        val testValue = "R$ 123,45"
        every {
            currencyFormat.format(testSimulation.investmentParameter.investedAmount)
        } returns testValue
        every {
            currencyFormat.format(testSimulation.grossAmountProfit)
        } returns testValue
        every {
            overviewSimulationDetailsMapper.map(testSimulation)
        } returns listOf()
        classToTest.initialize()
        verify(exactly = 1) {
            viewStateObserver.onChanged(
                OverviewSimulationViewState.Initial(
                    simulationTitle = R.string.overview_simulation_title,
                    simulationValue = testValue,
                    simulationTotalProfitTextDescription = R.string.overview_simulation_total_profit,
                    simulationTotalProfitTextHighlight = testValue,
                    simulationTotalProfitColorHighlight = R.color.accent,
                    simulationDetails = listOf(),
                    actionButtonLabel = R.string.overview_simulation_action
                )
            )
        }
    }

    @Test
    fun `should post navigation event on new simulation`() {
        classToTest.onNewSimulation()
        verify(exactly = 1) { navigationObserver.onChanged(CreateSimulationNavigationEvent) }
    }
}
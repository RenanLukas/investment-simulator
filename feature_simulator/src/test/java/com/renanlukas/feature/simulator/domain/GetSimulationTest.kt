package com.renanlukas.feature.simulator.domain

import com.renanlukas.feature.simulator.createSimulation
import com.renanlukas.feature.simulator.createSimulationRaw
import com.renanlukas.feature.simulator.data.SimulationRepository
import com.renanlukas.feature.simulator.data.SimulationRequest
import com.renanlukas.feature.simulator.domain.GetSimulation.Companion.DEFAULT_SIMULATION_INDEX
import com.renanlukas.feature.simulator.domain.GetSimulation.Companion.DEFAULT_SIMULATION_IS_TAX_FREE
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

class GetSimulationTest {

    private val simulationRepository: SimulationRepository = mockk()
    private val simulationMapper: SimulationMapper = mockk()

    private val classToTest = GetSimulation(simulationRepository, simulationMapper)

    private val testInvestedAmount = 123.45
    private val testMaturityDate = "2019-14-11"
    private val testRate = 5
    private val testRequest = SimulationRequest(
        testInvestedAmount,
        DEFAULT_SIMULATION_INDEX,
        testRate,
        DEFAULT_SIMULATION_IS_TAX_FREE,
        testMaturityDate
    )

    @Test
    fun `should return simulation if success fetching`() {
        every { simulationRepository.getSimulation(any()) } returns Single.just(createSimulationRaw())
        every { simulationMapper.map(any()) } returns createSimulation()
        classToTest.execute(testInvestedAmount, testMaturityDate, testRate)
        verify(exactly = 1) { simulationRepository.getSimulation(testRequest) }
    }

    @Test
    fun `should return error if failed to fetch simulation`() {
        every { simulationRepository.getSimulation(any()) } returns Single.error(
            NullPointerException()
        )
        verify(exactly = 0) {
            simulationMapper.map(any())
        }
    }
}
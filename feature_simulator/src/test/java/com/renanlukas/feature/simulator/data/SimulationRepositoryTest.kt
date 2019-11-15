package com.renanlukas.feature.simulator.data

import com.renanlukas.feature.simulator.createSimulationRaw
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

class SimulationRepositoryTest {

    private val simulationService: SimulationService = mockk()

    private val classToTest = SimulationRepository(simulationService)

    @Test
    fun `should fetch simulation on get simulation called`() {
        val request = SimulationRequest(
            123.45, "cdi", 5, false, "2019-14-11"
        )
        every {
            simulationService.fetchSimulation(any(), any(), any(), any(), any())
        } returns Single.just(createSimulationRaw())
        classToTest.getSimulation(request)
        verify(exactly = 1) {
            simulationService.fetchSimulation(
                investedAmount = request.investedAmount,
                index = request.index,
                rate = request.rate,
                isTaxFree = request.isTaxFree,
                maturityDate = request.maturityDate
            )
        }
    }
}
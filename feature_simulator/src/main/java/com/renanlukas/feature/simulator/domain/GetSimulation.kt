package com.renanlukas.feature.simulator.domain

import com.renanlukas.feature.simulator.data.SimulationRepository
import com.renanlukas.feature.simulator.data.SimulationRequest
import io.reactivex.Single
import javax.inject.Inject

class GetSimulation @Inject constructor(
    private val simulationRepository: SimulationRepository,
    private val simulationMapper: SimulationMapper
) {

    fun execute(investedAmount: Double, maturityDate: String, rate: Int): Single<Simulation> =
        simulationRepository.getSimulation(
            SimulationRequest(
                investedAmount,
                DEFAULT_SIMULATION_INDEX,
                rate,
                DEFAULT_SIMULATION_IS_TAX_FREE,
                maturityDate
            )
        ).map { simulationMapper map it }

    companion object {
        internal const val DEFAULT_SIMULATION_INDEX = "CDI"
        internal const val DEFAULT_SIMULATION_IS_TAX_FREE = false
    }
}
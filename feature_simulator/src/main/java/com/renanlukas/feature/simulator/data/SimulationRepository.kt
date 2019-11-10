package com.renanlukas.feature.simulator.data

import io.reactivex.Single
import javax.inject.Inject

class SimulationRepository @Inject constructor(
    private val simulationService: SimulationService
) {

    fun getSimulation(simulationRequest: SimulationRequest): Single<SimulationRaw> =
        with(simulationRequest) {
            simulationService.fetchSimulation(investedAmount, index, rate, isTaxFree, maturityDate)
        }
}
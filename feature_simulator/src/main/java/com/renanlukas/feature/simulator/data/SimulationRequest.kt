package com.renanlukas.feature.simulator.data

data class SimulationRequest(
    val investedAmount: Double,
    val index: String,
    val rate: Int,
    val isTaxFree: Boolean,
    val maturityDate: String
)
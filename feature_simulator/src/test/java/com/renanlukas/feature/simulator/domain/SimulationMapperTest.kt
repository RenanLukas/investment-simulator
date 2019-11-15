package com.renanlukas.feature.simulator.domain

import com.renanlukas.feature.simulator.createSimulation
import com.renanlukas.feature.simulator.createSimulationRaw
import org.junit.Assert.assertEquals
import org.junit.Test

class SimulationMapperTest {

    private val classToTest = SimulationMapper()

    @Test
    fun `should map simulation raw to simulation`() {
        val expected = createSimulationRaw()
        val actual = createSimulation()
        assertEquals(classToTest.map(expected), actual)
    }
}
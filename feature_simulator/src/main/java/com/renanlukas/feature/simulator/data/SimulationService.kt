package com.renanlukas.feature.simulator.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SimulationService {

    @GET("calculator/simulate")
    fun fetchSimulation(
        @Query("investedAmount") investedAmount: Double,
        @Query("index") index: String,
        @Query("rate") rate: Int,
        @Query("isTaxFree") isTaxFree: Boolean,
        @Query("maturityDate") maturityDate: String
    ): Single<SimulationRaw>
}
package com.renanlukas.feature.simulator.presentation.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renanlukas.feature.core.presentation.BaseViewModel
import com.renanlukas.feature.simulator.presentation.overview.OverviewSimulationViewState.Initial
import javax.inject.Inject

class OverviewSimulationViewModel @Inject constructor() : BaseViewModel() {

    private val mutableViewState: MutableLiveData<OverviewSimulationViewState> = MutableLiveData()
    val viewState: LiveData<OverviewSimulationViewState> = mutableViewState

    override fun initialize() {
        mutableViewState.postValue(
            //TODO
            Initial
        )
    }
}
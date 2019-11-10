package com.renanlukas.feature.simulator.presentation.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renanlukas.feature.core.presentation.BaseViewModel
import com.renanlukas.feature.simulator.domain.GetSimulation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateSimulationViewModel @Inject constructor(
    private val getSimulation: GetSimulation
) : BaseViewModel() {

    private val mutableViewState: MutableLiveData<CreateSimulationViewState> = MutableLiveData()
    val viewState: LiveData<CreateSimulationViewState> = mutableViewState

    private fun getSimulation() =
        getSimulation.execute(
            123.45, 50, "2020-02-02"
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val a = 0
            }, {
                val a = 0
            })
}
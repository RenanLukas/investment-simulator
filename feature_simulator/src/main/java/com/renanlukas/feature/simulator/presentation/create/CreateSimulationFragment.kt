package com.renanlukas.feature.simulator.presentation.create

import androidx.lifecycle.Observer
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.BaseViewModelFragment
import com.renanlukas.feature.core.ui.*
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.di.create.DaggerSimulatorComponent
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationViewState.*
import kotlinx.android.synthetic.main.fragment_create_simulation.*
import javax.inject.Inject

class CreateSimulationFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: CreateSimulationViewModelFactory

    override val viewModel by viewModel<CreateSimulationViewModel> { viewModelFactory }

    override fun layoutResource() = R.layout.fragment_create_simulation

    override fun inject() {
        activity?.let {
            DaggerSimulatorComponent
                .builder()
                .coreComponent(CoreInjectHelper.provideCoreComponent(it.applicationContext))
                .build()
                .inject(this)
        }
    }

    override fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is Initial -> buildInitial(state)
                is SimulationChanged -> buildSimulationChanged(state)
                is Loading -> buildLoading()
                is Error -> buildError(state)
            }.exhaustive()
        })
    }

    private fun buildInitial(state: Initial) {
        with(state) {
            simulationAmount.bind(amountViewEntity)
            simulationMaturityDate.bind(maturityViewEntity)
            simulationCdiInvestment.bind(cdiViewEntity)
            simulateButton.bind(MainButtonView.Entity(actionButtonLabel)) {
                activity?.hideKeyboard()
                viewModel.onSimulate()
            }
        }
        updateSimulateButton(state.enableAction)
    }

    private fun buildSimulationChanged(update: SimulationChanged) {
        updateSimulateButton(update.enableAction)
    }

    private fun updateSimulateButton(enable: Boolean) {
        with(simulateButton) { if (enable) enable() else disable() }
    }

    private fun buildLoading() {
        loading.show()
    }

    private fun buildError(state: Error) {
        loading.hide()
        activity?.snackbar(getString(state.message))
    }

    companion object {
        @JvmStatic
        fun newInstance(): CreateSimulationFragment = CreateSimulationFragment()
    }
}
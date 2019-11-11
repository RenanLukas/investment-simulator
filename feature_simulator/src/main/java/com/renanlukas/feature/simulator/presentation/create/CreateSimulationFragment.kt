package com.renanlukas.feature.simulator.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.renanlukas.feature.core.di.CoreInjectHelper
import com.renanlukas.feature.core.presentation.BaseViewModelFragment
import com.renanlukas.feature.core.ui.MainButtonView
import com.renanlukas.feature.core.ui.TitleInputView
import com.renanlukas.feature.simulator.R
import com.renanlukas.feature.simulator.di.DaggerSimulatorComponent
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationViewState.CreateSimulationInitial
import com.renanlukas.feature.simulator.presentation.create.CreateSimulationViewState.UpdateSimulation
import kotlinx.android.synthetic.main.fragment_create_simulation.*
import javax.inject.Inject

class CreateSimulationFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: CreateSimulationViewModelFactory

    override val viewModel by viewModel<CreateSimulationViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_create_simulation, container, false)

    override fun inject() {
        activity?.let {
            DaggerSimulatorComponent
                .builder()
                .coreComponent(CoreInjectHelper.provideCoreComponent(it.applicationContext))
                .build()
                .inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is CreateSimulationInitial -> buildCreateSimulationInitial(state)
                is UpdateSimulation -> buildUpdateSimulation(state)
            }
        })
    }

    private fun buildUpdateSimulation(state: UpdateSimulation) {
        simulateButton.isEnabled = state.isSimulateEnabled
    }

    private fun buildCreateSimulationInitial(state: CreateSimulationInitial) {
        with(state) {
            simulationAmount.bind(
                TitleInputView.Entity(
                    titleValue = getString(amountTitle),
                    hintValue = getString(amountHint),
                    isMandatory = amountMandatory
                )
            )
            simulationMaturityDate.bind(
                TitleInputView.Entity(
                    titleValue = getString(maturityDateTitle),
                    hintValue = getString(maturityDateHint),
                    isMandatory = maturityDateMandatory
                )
            )
            simulationCdiInvestment.bind(
                TitleInputView.Entity(
                    titleValue = getString(cdiPercentageTitle),
                    hintValue = getString(cdiPercentageHint),
                    isMandatory = cdiPercentageMandatory
                )
            )
            simulateButton.bind(
                MainButtonView.Entity(
                    getString(actionButtonLabel)
                )
            ) { viewModel.onSimulate() }
        }
    }

    companion object {
        fun newInstance(): CreateSimulationFragment = CreateSimulationFragment()
    }
}
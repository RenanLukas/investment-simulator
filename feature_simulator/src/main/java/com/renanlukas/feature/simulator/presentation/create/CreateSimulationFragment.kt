package com.renanlukas.feature.simulator.presentation.create

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAmountInput()
        setupMaturityDateInput()
        setupCdiInvestmentInput()
    }

    private fun setupAmountInput() {
        simulationAmount.addTextChangedListener { text, isDeleting ->
            viewModel.onAmountChanged(
                rawAmount = text,
                rawMaturityDate = simulationMaturityDate.inputText(),
                rawCdiInvestment = simulationCdiInvestment.inputText(),
                isDeleting = isDeleting
            )
        }
        viewModel.amountField.observe(viewLifecycleOwner, Observer {
            simulationAmount.bindInputValue(it.value)
        })
    }

    private fun setupMaturityDateInput() {
        simulationMaturityDate.addTextChangedListener { text, _ ->
            viewModel.onMaturityDateChanged(
                rawMaturityDate = text,
                rawAmount = simulationAmount.inputText(),
                rawCdiInvestment = simulationCdiInvestment.inputText()
            )
        }
        viewModel.maturityDateField.observe(viewLifecycleOwner, Observer { (input, showError) ->
            with(simulationMaturityDate) {
                bindInputValue(input)
                if (showError) showError() else hideError()
            }
        })
    }

    private fun setupCdiInvestmentInput() {
        simulationCdiInvestment.addTextChangedListener { text, isDeleting ->
            viewModel.onCdiInvestmentChanged(
                rawCdiInvestment = text,
                rawAmount = simulationAmount.inputText(),
                rawMaturityDate = simulationMaturityDate.inputText(),
                isDeleting = isDeleting
            )
        }
        viewModel.cdiInvestmentField.observe(viewLifecycleOwner, Observer {
            simulationCdiInvestment.bindInputValue(it.value)
        })
    }

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
            simulateButton.bind(MainButtonView.Entity(Text.Resource(actionButtonLabel))) {
                activity?.hideKeyboard()
                viewModel.onSimulate(
                    amount = simulationAmount.inputText(),
                    maturityDate = simulationMaturityDate.inputText(),
                    cdiInvestment = simulationCdiInvestment.inputText()
                )
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
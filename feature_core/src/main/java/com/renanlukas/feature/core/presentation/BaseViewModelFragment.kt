package com.renanlukas.feature.core.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseViewModelFragment : BaseInjectionFragment() {

    abstract val viewModel: BaseViewModel

    inline fun <reified T : BaseViewModel> viewModel(
        crossinline factory: () -> ViewModelProvider.Factory
    ): Lazy<T> =
        lazy { ViewModelProviders.of(this, factory()).get(T::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize()
    }
}
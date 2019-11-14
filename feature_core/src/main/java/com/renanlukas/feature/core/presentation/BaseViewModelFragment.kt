package com.renanlukas.feature.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseViewModelFragment : BaseInjectionFragment() {

    abstract val viewModel: BaseViewModel

    @LayoutRes
    abstract fun layoutResource(): Int

    abstract fun observeViewState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutResource(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
        viewModel.initialize()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigationSingleLiveEvent.observe(
            viewLifecycleOwner, Observer { navigate(it) }
        )
    }

    inline fun <reified VM : BaseViewModel> viewModel(
        crossinline factory: () -> ViewModelProvider.Factory
    ): Lazy<VM> =
        lazy { ViewModelProviders.of(this, factory()).get(VM::class.java) }
}
package com.renanlukas.feature.core.presentation

import androidx.lifecycle.ViewModel
import com.renanlukas.feature.core.navigator.NavigationEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val navigationSingleLiveEvent: SingleLiveEvent<NavigationEvent> = SingleLiveEvent()

    abstract fun initialize()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
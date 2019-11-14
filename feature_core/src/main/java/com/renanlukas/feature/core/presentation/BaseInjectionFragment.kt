package com.renanlukas.feature.core.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.renanlukas.feature.core.navigator.NavigationEvent
import com.renanlukas.feature.core.navigator.Navigator

abstract class BaseInjectionFragment : Fragment() {

    abstract fun inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inject()
        super.onViewCreated(view, savedInstanceState)
    }

    fun navigate(navigationEvent: NavigationEvent) =
        if (activity is Navigator) {
            (activity as Navigator).navigate(navigationEvent)
        } else throw IllegalStateException("Host Activity must implement Navigator")
}
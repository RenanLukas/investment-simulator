package com.renanlukas.feature.core.di

import android.content.Context

object CoreInjectHelper {

    fun provideCoreComponent(applicationContext: Context): CoreComponent =
        if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalArgumentException("Application must implement CoreComponentProvider")
        }
}
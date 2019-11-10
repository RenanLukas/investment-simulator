package com.renanlukas.feature.core.di

import com.renanlukas.feature.core.data.NetworkModule
import dagger.Component
import javax.inject.Singleton

//@Singleton
@Component(modules = [NetworkModule::class])
interface CoreComponent
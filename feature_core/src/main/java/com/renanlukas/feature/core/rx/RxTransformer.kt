package com.renanlukas.feature.core.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.withIOScheduler(): Single<T> =
    compose { single ->
        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
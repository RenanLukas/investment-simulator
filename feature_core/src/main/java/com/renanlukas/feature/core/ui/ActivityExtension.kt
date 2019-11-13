package com.renanlukas.feature.core.ui

import android.app.Activity
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar

typealias TransactionAndFragment = Pair<FragmentTransaction, Fragment>

infix fun AppCompatActivity.replace(fragment: Fragment): TransactionAndFragment =
    Pair(supportFragmentManager.beginTransaction(), fragment)

infix fun TransactionAndFragment.into(@IdRes container: Int) =
    this.first.replace(container, this.second)
        .commit()

fun Activity.snackbar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
}
package com.ahmaddudayef.moviesmade.util

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.getTestObserver() = TestObserver<T>().apply {
    observeForever(this)
}
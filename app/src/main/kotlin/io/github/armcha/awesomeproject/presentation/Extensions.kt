package io.github.armcha.awesomeproject.presentation

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.onClick(function: () -> Unit) {
    setOnClickListener {
        function()
    }
}

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun uiThread(task: () -> Unit) {
    if (Looper.getMainLooper().thread == Thread.currentThread()) {
        task()
    } else {
        Handler(Looper.getMainLooper()).post { task() }
    }
}


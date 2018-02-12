package io.github.armcha.awesomeproject

import android.app.Application
import io.github.armcha.awesomeproject.injector.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.injectApp(this)
    }
}
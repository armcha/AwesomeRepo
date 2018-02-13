package io.github.armcha.awesomeproject

import android.app.Application
import io.github.armcha.awesomeproject.injection.Injection

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injection.injectApp(this)
    }
}
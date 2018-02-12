package io.github.armcha.awesomeproject.injector

import android.app.Application
import io.github.armcha.awesomeproject.data.api.Api
import io.github.armcha.awesomeproject.data.json_reader.JsonReader
import io.github.armcha.awesomeproject.data.api.MockUserApi
import io.github.armcha.awesomeproject.data.parser.Parser
import io.github.armcha.awesomeproject.data.local.UserCache
import io.github.armcha.awesomeproject.data.repository.UserDataRepository
import io.github.armcha.awesomeproject.data.repository.UserRepository
import io.github.armcha.awesomeproject.executor.BackgroundExecutor
import io.github.armcha.awesomeproject.executor.Executor
import io.github.armcha.awesomeproject.presentation.main.MainContract
import io.github.armcha.awesomeproject.presentation.main.MainPresenter
import kotlin.properties.Delegates

object Injector {

    private var application: Application by Delegates.notNull()

    fun injectApp(application: Application) {
        this.application = application
    }

    fun provideApplication() = application

    fun provideApi(): Api = MockUserApi(provideParser(), provideJsonReader())

    fun provideJsonReader() = JsonReader(provideApplication())

    fun provideParser() = Parser()

    fun provideUserCache() = UserCache

    fun provideBackgroundExecutor(): Executor = BackgroundExecutor

    fun provideUserRepository(): UserRepository = UserDataRepository(provideApi(), provideUserCache())

    fun provideMainPresenter(): MainContract.Presenter = MainPresenter(provideUserRepository(), provideBackgroundExecutor())
}
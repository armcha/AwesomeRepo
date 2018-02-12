package io.github.armcha.awesomeproject.presentation.base

interface BaseContract {

    interface View

    interface Presenter<V : BaseContract.View> {

        val view: V?

        fun attachView(view: V)

        fun detachView()

        fun onPresenterDestroy() {}
    }
}

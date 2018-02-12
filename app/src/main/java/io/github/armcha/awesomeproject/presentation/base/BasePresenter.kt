package io.github.armcha.awesomeproject.presentation.base


import android.arch.lifecycle.LifecycleObserver

abstract class BasePresenter<V : BaseContract.View> : LifecycleObserver, BaseContract.Presenter<V> {

    override var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}

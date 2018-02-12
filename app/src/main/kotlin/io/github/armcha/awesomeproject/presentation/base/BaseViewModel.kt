package io.github.armcha.awesomeproject.presentation.base

import android.arch.lifecycle.ViewModel

class BaseViewModel<V : BaseContract.View, P : BaseContract.Presenter<V>> : ViewModel() {

    var presenter: P? = null

    override fun onCleared() {
        presenter?.onPresenterDestroy()
        presenter = null
        super.onCleared()
    }
}

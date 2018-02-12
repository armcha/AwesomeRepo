package io.github.armcha.awesomeproject.presentation.main

import io.github.armcha.awesomeproject.data.repository.UserRepository
import io.github.armcha.awesomeproject.executor.Executor
import io.github.armcha.awesomeproject.model.User
import io.github.armcha.awesomeproject.presentation.base.BasePresenter
import io.github.armcha.awesomeproject.presentation.base.Status
import io.github.armcha.awesomeproject.presentation.uiThread
import java.util.concurrent.Future

class MainPresenter(private val userRepository: UserRepository, private val executor: Executor)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private var userStatus = Status.IDLE
    private var future: Future<Unit>? = null

    override fun onStart() {
        when (userStatus) {
            Status.LOADING -> {
                view?.showLoading()
            }
            else -> {
                view?.showLoading()
                userStatus = Status.LOADING
                future = executor.execute(task = {
                    userRepository.getUsers {
                        uiThread {
                            userStatus = Status.SUCCESS
                            view?.hideLoading()
                            view?.setUpUserList(it)
                            view?.showAddButton()
                        }
                    }
                })
            }
        }
    }

    override fun onPresenterDestroy() {
        future?.cancel(true)
    }

    override fun createNewUser(name: String, surName: String) {
        val newUser = User(name.capitalize(), surName.capitalize())
        userRepository.addUser(newUser)
        view?.addNewUserToList(newUser)
    }

    override fun handleFabClick() {
        view?.showAddUserDialog()
    }
}
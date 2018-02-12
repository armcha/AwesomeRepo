package io.github.armcha.awesomeproject.presentation.main

import io.github.armcha.awesomeproject.presentation.base.BaseContract
import io.github.armcha.awesomeproject.model.User

interface MainContract {


    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showAddButton()

        fun showAddUserDialog()

        fun setUpUserList(list: List<User>)

        fun addNewUserToList(user: User)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun createNewUser(name: String, surName: String)

        fun handleFabClick()

        fun onStart()
    }
}
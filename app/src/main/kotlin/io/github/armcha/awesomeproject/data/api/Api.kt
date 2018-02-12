package io.github.armcha.awesomeproject.data.api

import io.github.armcha.awesomeproject.model.User

interface Api {

    fun getUsers(onReceive: (List<User>) -> Unit)
}
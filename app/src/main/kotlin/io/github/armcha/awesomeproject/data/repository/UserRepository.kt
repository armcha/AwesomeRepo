package io.github.armcha.awesomeproject.data.repository

import io.github.armcha.awesomeproject.model.User

interface UserRepository {

    fun getUsers(onReceive: (List<User>) -> Unit)

    fun addUser(user: User)
}
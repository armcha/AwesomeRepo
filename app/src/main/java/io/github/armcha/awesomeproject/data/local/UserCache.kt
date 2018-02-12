package io.github.armcha.awesomeproject.data.local

import io.github.armcha.awesomeproject.model.User

object UserCache {

    val userList: MutableList<User> = mutableListOf()

    val hasCachedUsers
        get() = userList.isNotEmpty()

    fun addUsers(newUsers: List<User>) {
        userList.addAll(newUsers)
    }

    fun addUser(newUser: User) {
        userList.add(newUser)
    }

    fun evictAll(){
        userList.clear()
    }
}
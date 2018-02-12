package io.github.armcha.awesomeproject.data.repository

import io.github.armcha.awesomeproject.data.api.Api
import io.github.armcha.awesomeproject.data.local.UserCache
import io.github.armcha.awesomeproject.model.User

class UserDataRepository(private val api: Api, private val userCache: UserCache) : UserRepository {

    override fun getUsers(onReceive: (List<User>) -> Unit) {
        if (userCache.hasCachedUsers) {
            onReceive(userCache.userList)
        } else api.getUsers {
            userCache.addUsers(it)
            onReceive(it)
        }
    }

    override fun addUser(user: User) {
        userCache.addUser(user)
    }
}
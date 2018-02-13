package io.github.armcha.awesomeproject.data.api.parser

import io.github.armcha.awesomeproject.model.User
import org.json.JSONObject

class Parser {

    private val USER_ARRAY = "users"
    private val KEY_NAME = "name"
    private val KEY_SUR_NAME = "surName"

    fun parseUsers(usersJson: JSONObject): List<User> {
        val usersArray = usersJson.getJSONArray(USER_ARRAY)
        return (0 until usersArray.length())
                .map { usersArray.getJSONObject(it) }
                .map {
                    val userName = it.getString(KEY_NAME)
                    val userSurName = it.getString(KEY_SUR_NAME)
                    User(userName, userSurName)
                }
    }
}
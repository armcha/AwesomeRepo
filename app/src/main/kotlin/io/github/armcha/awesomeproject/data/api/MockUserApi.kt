package io.github.armcha.awesomeproject.data.api

import io.github.armcha.awesomeproject.data.api.json_reader.JsonReader
import io.github.armcha.awesomeproject.data.api.parser.Parser
import io.github.armcha.awesomeproject.model.User

class MockUserApi(private val parser: Parser, private val jsonReader: JsonReader) : Api {

    private val JSON_NAME = "users.json"

    override fun getUsers(onReceive: (List<User>) -> Unit) {

        val json = jsonReader.readJson(JSON_NAME)
        Thread.sleep(3000)

        val users = parser.parseUsers(json)
        Thread.sleep(2000)

        onReceive(users)
    }
}
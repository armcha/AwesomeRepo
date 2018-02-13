package io.github.armcha.awesomeproject.data.api.json_reader

import android.app.Application
import org.json.JSONObject

class JsonReader(private val application: Application) {

    fun readJson(jsonName: String): JSONObject {
        val inputStream = application.assets.open(jsonName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        return JSONObject(json)
    }
}
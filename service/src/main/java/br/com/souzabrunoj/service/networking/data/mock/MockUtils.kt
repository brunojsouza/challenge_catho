package br.com.souzabrunoj.service.networking.data.mock

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockUtils {
    companion object {
        fun loadJsonFromResources(filename: String) = this::class.java.getResource("/$filename")?.readText() ?: ""
    }
}

inline fun <reified T> fromJson(jasonPath: String): T {
    val json = MockUtils.loadJsonFromResources(jasonPath)
    return Gson().fromJson(json, object: TypeToken<T>(){}.type)
}
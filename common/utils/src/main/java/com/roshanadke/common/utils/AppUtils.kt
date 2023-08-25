package com.roshanadke.common.utils

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken


object GsonProvider {
    val gson: Gson = Gson()
}

fun encodeObjectToUri(obj: Any): String {
    val jsonString = Gson().toJson(obj)
    return Uri.encode(jsonString)
}

fun objectToString(obj: Any): String? {
    return GsonProvider.gson.toJson(obj)
}

inline fun <reified T> stringToObject(json: String): T? {
    return try {
        val typeToken = object : TypeToken<T>() {}.type
        Gson().fromJson<T>(json, typeToken)
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }
}

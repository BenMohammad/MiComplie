package com.benmohammad.micomplie.core

import android.util.Log
import java.io.File
import java.nio.charset.Charset

fun saveTextFile(text: String, path: String): String {
    return try {
        File(path).apply {
            writeText(text, Charset.defaultCharset())
        }

        "Successfully saved"
    } catch (e: Exception) {
        Log.i("saveTextFile", e.printStackTrace().toString())
        "Could not Save changes. see logs for more info"
    }

}

fun loadTextFromFile(path: String): String? {
    try {
        File(path).apply {
            return readText(Charset.defaultCharset())
        }
    } catch (e: Exception) {
        Log.i("loadFromFile", e.printStackTrace().toString())
        return null
    }
}
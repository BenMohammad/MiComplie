package com.benmohammad.micomplie.data

import android.os.Environment
import com.benmohammad.micomplie.core.loadTextFromFile
import com.benmohammad.micomplie.core.saveTextFile
import java.io.File

class CodeRepository() {

    fun getCode(): String {
        val code = loadTextFromFile(
            FULL_PATH
        ).orEmpty()

        return code
    }

    fun saveCode(text: String): String {
        if(!File("${Environment.getExternalStorageDirectory()}${CODE_TEXT_FOLDER_PATH}").exists()) {
            if(File("${Environment.getExternalStorageDirectory()}${CODE_TEXT_FOLDER_PATH}")
                    .mkdir()
            )

                File(FULL_PATH).createNewFile()
        }
        return saveTextFile(
            text,
            FULL_PATH
        )
    }

    companion object {
        private const val CODE_TEXT_FILE = "/main.txt"
        private const val CODE_TEXT_FOLDER_PATH = "/MiCompile"
        private var FULL_PATH = "${Environment.getExternalStorageDirectory()}${CODE_TEXT_FOLDER_PATH}${CODE_TEXT_FILE}"


        @Volatile
        private var instance: CodeRepository? = null

        fun getInstance() =
            instance?: synchronized(this) {
                instance ?:
                        CodeRepository().also { instance = it }
            }

    }

}
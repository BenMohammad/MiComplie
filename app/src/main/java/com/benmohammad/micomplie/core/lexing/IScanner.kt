package com.benmohammad.micomplie.core.lexing

interface IScanner {

    fun getErrorInfo(message: String = ""): String

    fun getTokenInList(list: List<String>): Int

    fun isKeyword(keyword: String): Boolean

    fun skipInt(): String
    fun skipFloat(): String
    fun skipId(): String
    fun skipStr(): String

    fun getToken(keyword: String): String
}
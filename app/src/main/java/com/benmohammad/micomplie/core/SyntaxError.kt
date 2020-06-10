package com.benmohammad.micomplie.core

import java.lang.Exception

class SyntaxError(
    private val errorInfo: String
) : Exception() {

    fun print(): String {
        return errorInfo

    }

}
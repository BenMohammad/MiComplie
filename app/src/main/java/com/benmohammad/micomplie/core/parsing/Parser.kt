package com.benmohammad.micomplie.core.parsing

import com.benmohammad.micomplie.core.SyntaxError

class Parser(private val parse: IParser) {

    fun execute(): String {
        return try {
            parse.execute()
        } catch (e: SyntaxError) {
            e.print()
        }
    }
}
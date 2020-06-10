package com.benmohammad.micomplie.core.parsing

import android.util.Log
import com.benmohammad.micomplie.core.SemanticActionEnum
import com.benmohammad.micomplie.core.SyntaxError
import com.benmohammad.micomplie.core.lexing.IScanner
import java.util.*

class SkipRegular(private val scanner: IScanner): IParser {

    private val stack = Stack<String>()

    override fun execute(): String {
        skipR()
        if(stack.isNotEmpty()) return stack.pop()

        throw SyntaxError(scanner.getErrorInfo())
    }

    private fun skipR() {
        skipN()
        skipR1()
    }

    private fun skipR1() {
        if(scanner.isKeyword("|")) {
            scanner.getToken("|")
            skipN()
            doAction(SemanticActionEnum.SA_OR, "|")
            skipR()
        }
    }


    private fun skipN() {
        skipP()
        skipS1()
    }

    private fun skipS1() {
        if(scanner.isKeyword("*")) {
            scanner.getToken("*")
            doAction(SemanticActionEnum.SA_MUL, "*")
            skipS1()
        }
    }

    private fun skipP() {
        when(scanner.getTokenInList(arrayListOf("(", "#id"))) {
            0 -> {
                scanner.getToken("(")
                skipR()
                scanner.getToken(")")
            }

            1 -> {
                doAction(SemanticActionEnum.SA_ID, scanner.skipId())
            }
            else -> throw SyntaxError(scanner.getErrorInfo("Regular element expected: ( , id"))
        }
    }

    private fun doAction(action: SemanticActionEnum, tokenVal: String = "0") {
        val l: String
        val r: String

        when(action){

            SemanticActionEnum.SA_OR, SemanticActionEnum.SA_DOT -> {
                r = stack.pop()
                l = stack.pop()
                stack.push(tokenVal + l + r)
            }

            SemanticActionEnum.SA_STAR -> {
                stack.push("*" + stack.pop())
            }

            SemanticActionEnum.SA_ID -> {
                stack.push(tokenVal)
            }

            else -> {}
        }
        Log.i("doAction", stack.toString())

    }}
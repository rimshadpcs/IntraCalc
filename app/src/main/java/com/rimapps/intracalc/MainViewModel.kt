package com.rimapps.intracalc

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainViewModel : ViewModel() {

    val exp = MutableLiveData<String>()
    val answer = MutableLiveData<String>()

    init {
        exp.value = "0"
        answer.value = "0"
    }

    fun delete() {
        val string = exp.value!!
        if (string.length == 1 || (string.length == 2 && string[0] == '-'))
            reset()
        else {
            exp.value = string.substring(0, string.length - 1)
            valuateAgain()
        }
    }

    private fun valuateAgain() {
        val text = getFormatted()
        Log.d("number", text)
        val expression = ExpressionBuilder(text).build()

        try {
            val result = expression.evaluate()
            if (Math.floor(result) == result)
                answer.value = result.toString()
            else
                answer.value = result.toString()
        } catch (e: Exception) {
            answer.value="Invalid"
        }
    }

    private fun getFormatted(): String {
        var string = exp.value!!
        Log.d("number", string)
        if (string[string.length - 1] == '.')
            string = string.substring(0, string.length - 1)

        while (!string[string.length - 1].isDigit() && string.isNotEmpty())
            string = string.substring(0, string.length - 1)

        var countO = 0
        var countC = 0
        for (i in string) {
            if (i == '(')
                countO++
            if (i == ')')
                countC++
        }
        if (countC == countO)
            return string
        for (i in 1..countO - countC)
            string += ")"

        return string
    }

    fun calculate() {
        exp.value = answer.value
    }

    fun reset() {
        exp.value = "0"
        answer.value = "0"
    }

    fun appendOperator(o: Char) {
        if (o == '-' && exp.value == "0") {
            exp.value = "-"
            answer.value = "-"
            return
        }
        var string = exp.value!!
        if (string[string.length - 1] == '.')
            string = string.substring(0, string.length - 1)
        if (string[string.length - 1] == '(' && o != '-')
            return
        if (!string[string.length - 1].isDigit() && o != '-')
            string = string.substring(0, string.length - 1)
        string += o.toString()
        exp.value = string
    }

    fun bOpen() {
        var string = exp.value!!
        if (string[string.length - 1] == '.')
            string = string.substring(0, string.length - 1)
        string += "("
        exp.value = string
    }

    fun bClose() {
        var string = exp.value!!
        var countO = 0
        var countC = 0
        for (i in string) {
            if (i == '(')
                countO++
            if (i == ')')
                countC++
        }

        if (string[string.length - 1] == '(') {
            string = string.substring(0, string.length - 1)
            exp.value = string
            return
        }

        if (countC < countO) {
            string += ")"
            exp.value = string
        }
    }

    fun appendNumber(n: String) {
        var string = exp.value!!
        if (n == ".") {
            var lastDot = false
            for (i in string) {
                if (i.isDigit())
                    continue
                lastDot = i == '.'
            }
            if (lastDot)
                return
        }
        if (string.trim() == "0")
            string = ""
        if (n == "." && string == "")
            string = "0"
        string += n
        exp.value = string
        Log.d("number", exp.value!!)
        valuateAgain()
    }
    fun appendComma(n: String){

        var string = exp.value!!
        if(n.isDigitsOnly()){
            for (i in string){


            }

        }
    }

}
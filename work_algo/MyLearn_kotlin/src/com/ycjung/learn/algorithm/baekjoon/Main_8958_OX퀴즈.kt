package com.ycjung.learn.algorithm.baekjoon

import java.util.*

class Main_8958_OX퀴즈 {
}

fun main() {

    val maxRows: Int = readLine()!!.toInt()

    val inputs = Array(maxRows) {CharArray(0) }
    for(i in 0 until maxRows) {
        val input = readLine() ?: ""

        inputs[i] = input.toCharArray()
    }


    for(i in 0 until maxRows) {
        var score = 0
        var chainNum = 0

        for (j in 0 until inputs[i].size) {
            if(inputs[i][j] == 'O') {
                chainNum++
            } else {
                chainNum = 0
            }

            score += chainNum;
        }

        println("$score")
    }
}
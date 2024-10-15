package com.ycjung.learn.algorithm.baekjoon

class Main_10798_세로읽기 {
}

fun main() {
    val maxRows = 5  // 행의 개수
    val maxChars = 15  // 각 행의 최대 문자 개수
    val inputs = Array(maxRows) { CharArray(maxChars) { ' ' } }  // 2차원 배열 선언 및 초기화

    // 사용자 입력 받기
    for (i in 0 until maxRows) {
        val input = readLine()?.take(maxChars) ?: ""  // 최대 15자까지 입력받기

        // 짧은 입력은 공백으로 채우기
        val paddedInput = input.padEnd(maxChars, ' ')
        inputs[i] = paddedInput.toCharArray()  // 문자열을 CharArray로 변환하여 저장
    }

    // 열을 기준으로 각 행의 문자를 조합하여 출력
    val result = StringBuilder()  // 결과를 누적할 StringBuilder 사용
    for (j in 0 until maxChars) {
        for (i in 0 until maxRows) {
            if (inputs[i][j] != ' ') {  // 공백이 아닌 경우만 추가
                result.append(inputs[i][j])
            }
        }
    }

    // 최종 결과 출력
    println("$result")
}
package test01

fun main() {
    val num = 100
    when (num) {
        100 -> println("100")
        200 -> println("200")
        else -> println("error")
    }

    val score = readln().toInt()
    println("input socre : $score") // 90~100 : grade A, 80~ 90 : grade B

    when (score) {
        in 90..100 -> println("in 90 found")
        in 80..80 -> println("in 80 found")
        in 60..60 -> println("in 60 found")
        else -> println("error")
    }

    val grade = when(score/10) {
        else -> 'F'
    }
}
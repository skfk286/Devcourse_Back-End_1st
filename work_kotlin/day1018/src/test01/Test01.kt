package test01

fun sum(x1: Int, x2: Int): Int {
    return x1 + x2
}

val sum1 = { x1: Int, x2: Int -> x1 + x2}
val sum2 = { -> 10 + 20} // 매개변수가 없는 람다 함수 정의
val sum2_1 = {10 + 20}
val sum3 = {x1: Int, x2: Int -> // 함수 내용이 여러 문장으로 된 경우의 리턴 값
    println("call sum3()....")
    x1 + x2
}

val lambdaFun = {x: Int -> x* 10}

fun main() {
    val result1 = sum(10, 20)
    println(result1)

    val result3 = sum3(10, 20)
    println(result3)
}
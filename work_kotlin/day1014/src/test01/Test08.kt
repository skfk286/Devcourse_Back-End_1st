package test01

fun main() {
    val arr1 = arrayOf(1, "yechan", true) // any
    // arr1 = arrayOf(2, "mucho", true) // error - val 이여서 불가능
    arr1[0] = 111
    println("size : ${arr1.size}, ${arr1[1]}, ${arr1[2]}")

    // arrayOf<타입> 으로 배열 데이터들의 타입 지정 가능.
    val arr2 = arrayOf<Int>(2, 3, 4, 5)

    // 기초 타입 배열을 위한 함수 별도로 있다.
    val arr3 = intArrayOf(1,2,3)
    val arr4 = doubleArrayOf(1.0,2.0,3.0,4.0,5.0)

    // 크기만 지정하고 null 채워둔 배열 생성 가능
    val arr5 = arrayOfNulls<Any>(5) // 크기가 5인 배열
    arr5[0] = 100
    arr5[1] = "hello"
}
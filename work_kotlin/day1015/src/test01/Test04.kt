package test01

fun main() {
    val list = listOf("aaa", "bbb", "ccc")

    for (str in list) {
        println(str)
    }
    println("\n-------------------------------")

    println(list.indices) // 리스트의 인덱스만 떼서 얻어온다.

    for(i in list.indices) {
        println("$i -- ${list[i]}")
    }
    println("\n-------------------------------")

    for((idx, value) in list.withIndex()) {
        println("$idx -- ${value}")
    }

}
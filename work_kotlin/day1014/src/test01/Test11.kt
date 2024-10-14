package test01

fun main() {
    var set = setOf("aaa", "bbb", "ccc")
    println("${set.size}, ${set.toString()}")

    // set.add("ddd")

    var mutableSet = mutableSetOf("aa", "bb")
    mutableSet.add("bbb")
    mutableSet.add("ccc")
    println("${mutableSet.size}, ${mutableSet.toString()}")

    var map = mutableMapOf<Int, String>(Pair(10, "Hi"), Pair(99, "Bye"))
    map.put(22, "Hello")
    map.put(99, "Yechan") // 이미 있는 정보는 덮어쓰기
    println(map.get(99))
    println(map.toString())

}
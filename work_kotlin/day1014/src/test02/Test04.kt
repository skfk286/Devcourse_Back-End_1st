package test02

fun sayHello(name: String, phone: String = "1234") {
    println("hello $name")
    println("phone $phone")
}

fun main() {
    sayHello("ycjung")
    println("-------------------")
    sayHello("grepp", "010-1234-5678")
    println("-------------------")
    sayHello(phone = "9999", name = "programmers")
}
package test02_etc

object MySingleton {
    var name = "ycjung"

    fun f1() {
        println("f1 call")
    }
}

fun main() {
//    val obj = MySingleton()
    println("Singleton 이라 객체 생성 불가, 그냥 클래스 명으로 접근. ${MySingleton.name}")

    MySingleton.f1()
}
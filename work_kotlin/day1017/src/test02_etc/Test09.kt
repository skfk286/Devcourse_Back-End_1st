package test02_etc

class Outer9 {
    var outerName: String = "ycjung"

    fun outerFun() {
        println("중첩 클래스의 외부 함수 호출 : ${outerName}")
    }

    class Inner9 {
        var innerName = "hi"
        fun innerFun() {
            // println("$outerName") // 접근 불가
            // outerFun() // 접근 불가
            println("중첩 클래스의 함수 호출 : $innerName")
        }
    }
}

fun main() {
    var obj = Outer9()
    var obj2 = Outer9.Inner9()
}
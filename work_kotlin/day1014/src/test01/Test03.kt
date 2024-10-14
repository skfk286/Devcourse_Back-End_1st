package test01

// 모든 변수는 자동 초기값 없음. 값 대입이 필수임.

// var global:String // error - 초기화 필수. 전역변수

class test03 {
    // var member:String // error - 초기화 필수. 멤버변수

    fun hello() {
        var local:String // 지역변수 - 초기화 필수 아님. 다만

        // println(local) // error - 초기화 안한 지역변수 읽기.
        local = "Hello World"
        println(local)

        // local = null // 모든 변수는 기본적으로 null 안됨.
        var local2:String? = null
    }
}

fun main(args: Array<String>) {

}
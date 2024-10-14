package test01

class test02 {
}

class User {
    fun hello() {
        println("객체에 포함되는 함수")
    }
}

fun main() {
    val v1:Int = 100
    val v2 = 200 // 변수에 자료형 생략해도 들어가는 데이터를 보고 유추됨.
    var v3 = 300

    // v1 = 999 // val 은 한번 정하고 나면 변경 불가능
    v3 = 999 // var 은 프로그램 진행하다 값이 변경되어도 괜찮음.

    val user = User() // 객체 생성해서 변수에 기억. 자바 참조개념 그대로. 생성자 호출. new 키워드 생략
    user.hello()

    val java = MyJava("예찬") // 자바로 무조건 클래스에는 생성자 필수로 있었으니, 그 생성자 호출하면서 객체 생성 하면 된다.
}
package test01

fun main() {
    var a1 = 10
    var a2 = a1 // 타입 추론은 알아서 대입되는 값 보고 할 수 있음.

    var a3:Int = 10 // 명시적으로 정의한 기초타입은.
    // var a4:Double = a3 // error -

    getUpper(10) // Any 타입이라 아무거나 넣을 수 있음.
    getUpper("hello")

    println(getUpper(10))
    println(getUpper("Programmers"))
}

fun getUpper(obj: Any):String {
    if (obj is String) { // 자바에서 instanceof > true 결과 나우면 obj 변수에 String으로 형변환까지 진행해준다.
        return obj.uppercase()
    }

    return "no String"
}


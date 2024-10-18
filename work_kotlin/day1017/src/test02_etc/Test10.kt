package test02_etc

class Outer10 {
    var outerName: String = "ycjung"

    fun outerFun() {
        println("중첩 클래스의 외부 함수 호출 : ${outerName}")
    }

    inner class Inner10 {
        var innerName = "hi"
        fun innerFun() {
            // println("$outerName") // 접근 불가
            // outerFun() // 접근 불가
            println("중첩 클래스의 함수 호출 : $innerName")
        }
    }

    val InnerObj = Inner10()
}

fun main() {
    var obj = Outer10()
    // var obj2 = Outer10.Inner10() // 내부 객체 생성 안됨(inner 키워드) => 바깥 클래스 객체 부터 먼저 생성해야 된다.
    var obj2 = Outer10().Inner10() // 이렇게..

    obj.outerName = "바깥 이름 변경"
    obj.outerFun() // 바깥 함수 호출
    obj.Inner10() // 맴버 클래스(inner class)의 객체 생성
//    obj.innerName = "안쪽 // 이름 변경"
//    obj.innerFun() // 에러

//    obj2.outerName = "바깥 이름 변경"
//    obj2.outerFun() // 바깥 함수 호출
//    obj2.Inner10() // 맴버 클래스(inner class)의 객체 생성
//    obj.innerFun() // outer 입장에서 못찾는다
}
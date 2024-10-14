package test01

const val CONST_VAL = 100 // 프로그램을 몇 번 실행하더라도 항상 동일한 값. 즉 상수. 이름 대문자 통일
// const val constVal2 = sum(1,2) // error - 함수 실행하기 전에 컴파일 시점에 알 수 없는 값은 못넣음.
val generalVal = sum(10, 20) // 프로그램을 여러 번 실행해서 그때마다 값이 바뀔 수 있는 애라서 변수에 포함

fun sum (n1:Int, n2:Int):Int {
    return n1 + n2
}

fun main() {
    var n1:Int = 100 // java 에서는 원시타입 int 있었지만 코틀린은 무조건 wrapper 클래스 객체를 쓴다고 생각하면 됨.
}
package test01

import java.util.*

fun main() {
    // Array 클래스로 객체 생성 Array(size){""} 또는 Array(size){초기화 값})
    // 코틀린의 변수는 전역, 멤버, 지역이고 이중에 지역은 초기화 자체가 필수 아니지만,
    // 전역, 멤버는 초기값 필수(자바에서는 null 자동 이었다.)
    // 여기서는 Array 도 클래스니깐 그 내부 배열 칸들을 멤버변수들 이라고 생각.
    // 그래서 초기값 명시적 필요
    var emptyArray = Array<String>(3){"abc"}
    emptyArray[0] = "hello"

    // 배열 각 칸의 식별은 인덱스로 하게 된다. 즉, 각 변수 이름이 0, 1, 2 라고 생각하면 그 식별값들을 넣고 모든 변수에게 각각 람다 함수를 실행시켜 넣어준다고 이해.
    var arr2 = Array(3){n -> n*2}
    println(Arrays.toString(arr2))
}
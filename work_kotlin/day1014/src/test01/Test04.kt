package test01

val fullname:String = "jungyechan"
//    set(value) { // error - val 는 나중에 값 변경이 안되는 애인데 setter 사용 불가
//        print("이름이 생성되고 있습니다.")
//        field = value
//    }
    get() {
        println("이름이 읽어지고(사용되고) 있습니다.")
        return field
    }

var name = "ycchan"
    set(value) {
        println("이름이 설정되고 있습니다. :" + value)
        field = value
    }
    get() {
        println("이름이 읽어지고(사용되고) 있습니다.")
        return field
    }

fun main() {
    name = "예찬" // 세터가 호출되고 있는 거다.

    println(name) // 게터 호출
}
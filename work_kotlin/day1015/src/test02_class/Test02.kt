package test02_class

// 주 생성자 매개변수는 이 클래스의 멤버 변수가 된다.
class MyClass2(val name: String, var age: Int) {}

fun main() {
    val obj = MyClass2("ycjung", 32)
    println("이름은 ${obj.name} 이고, 나이는 ${obj.age}")
    // obj.name = "하하" // error -
    obj.age = 33
}
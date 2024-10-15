package test02_class

// class MyClass

class MyClass(name:String, age:Int = 0) {
    init {
        println("주 생성자 호출")
    }
    var phone:String = "01012345678"
    fun myFunc() {

    }
}

fun main() {
    val myObj = MyClass("예찬", 20)
    val myObj2 = MyClass("그렙")
}
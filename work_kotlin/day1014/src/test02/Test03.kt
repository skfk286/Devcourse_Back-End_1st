package test02

fun func(vararg params: Any) {
    for(item in params) {
        println("func receive : $item")
    }
}

fun main() {
    func(10, 20, 30, "hello")
}
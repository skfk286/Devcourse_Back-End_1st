package test01

fun main() {
    var num = "Hello"
    println("문자열 내부에 변수값 포함시킬 때" + num + " 자바는 이렇게 이어붙이기")
    println("코틀린은 $num 이렇게 끼워넣기 가능 1")
    println("코틀린은 ${num + 100} 이렇게 끼워넣기 가능 2")

    var str = """
        동해 물과
        
        백두산이
        
        
        마르고 닳도록
    """.trimIndent() // trimIndent 들여쓰기 안 들어가게 설정된 상태
    println(str)
}
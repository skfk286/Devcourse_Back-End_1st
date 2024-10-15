package test01

fun main() {
    val arr1 = arrayOf(10, 20, 30) // 배열 -> 리스트로 바꾸고 싶을 때 선택지 (불변 리스트 or 가변 리스트)

    val list1 = arr1.toList() // 불변 리스트
    val list2 = arr1.toMutableList() // 가변리스트
    list2.add(500)
    list2.add(600)

    val arr2 = list2.toTypedArray() // 리스트 -> 배열
    ///////////////////////////////////////////////////////////////////////////
    val list3 = listOf(1, 2, arr1[0], arr1[1], arr1[2], 500, 600) // 배열에 있는 데이터 포함하면서 좀 더 추가하여 리스트 만들기
    val list4 = listOf(1,2, *arr1, 500, 600)
    ///////////////////////////////////////////////////////////////////////////
    val arr3 = arrayOf("abc", "tomato", "banana")
    myPrint(*arr3)
}

fun myPrint(vararg params: Any) {
    for (item in params) {
        println(item)
    }
}


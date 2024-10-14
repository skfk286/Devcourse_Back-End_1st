package test01

fun main() {
    val list = listOf("apple", "banana")
    println("${list[0]}, ${list.get(1)}")

    // list.add("tomato") // error - 이렇게 리스트에 새롭게 추가하는 형태는 안된다.
    // list[0] = "carrot" // error - 이렇게도 안됨.

    // 추 후에 데이터를 추가하거나 변경하거나 하는 작업이 필요한 경우 아래처럼 mutable 이라고 붙은 것을 쓰거나 ArrayList 쓰거나.
    val list2 = mutableListOf("aaa", "bbb")
    list2.add("ccc")
    list2.set(0, "zzz")
    list2[0] = "zzz" // 기존 리스트 항목의 값을 변경하는게 목적이면 그냥 이렇게 쓰는게 더 잘 읽힌다?

    val list3 = List(3){0} // 사이즈 + 초기값 설정으로 리스트 초기화도 가능

    val arrayList = ArrayList<String>() // java 컬렉션이 아니고 kotlin 컬렉션임. 패키지 보면..
    arrayList.add("aaa")
    arrayList.add("bbb")
    arrayList.add("ccc")
    arrayList.removeFirst() // 앞에서 뺴고 - "aaa" 삭제
    arrayList.removeLast() // 뒤에서 빼고 - "bbb" 삭제

    println("${arrayList.size} // ${arrayList[0]}")
}
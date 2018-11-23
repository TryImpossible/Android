package com.barry.kotlin

import java.io.File
import java.util.*

fun print(): Unit {
    println("Hello World!")
}

fun sum(x: Int, y: Int): Unit {
    println("sum of $x and $x is ${x + y}")
}

fun variables() {
    var a: Int = 1 // 立即赋值
    var b = 2 // 自动推断出`Int`类型
    var c: Int // 如果没有初始值不能省略
    c = 3 // 明确赋值

    var x = 5 // 自动推断出`Int`类型
    x += 1
    fun incrementX() {
        x += 1
    }

//    var a = 1
//    var s1 = "a is $a"
//
//    a = 2
//    val s2 = "${s1.replace("is", "was")}, but now is $a"
}

//fun maxOf(x: Int, y: Int): Int {
//    if (x > y) {
//        return x
//    } else {
//        return y
//    }
//}
fun maxOf(x: Int, y: Int) = if (x > y) x else y

fun parseInt(str: String): Int? = str.toInt()
fun printProduct(arg1: String, arg2: String) {
    var x = parseInt(arg1)
    var y = parseInt(arg2)

    // 直接使用 `x * y` 会导致编译错误，因为他们可能为null
    if (x != null && y != null) {
        // 在空检测后， x 与 y 会自动转换成非空值(non nullable)
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

//fun getStringLength(obj: Any): Int? {
//    if (obj is String) {
//        // `obj` 在该条件分支内自动转换成 `String`
//        return obj.length
//    }
//    // 在离开类型检测分支后， `obj`仍然是 `Any`类型
//    return null
//}

fun circle() {
    var items = listOf<String>("apple", "banana", "kiwifruit")
//    for (item in items) {
//        println(item)
//    }
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun describle(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

fun range() {
//    var x = 10
//    var y = 9
//    if (x in 1..y+1) {
//        println("fits in range")
//    }

//    val list = listOf<String>("a", "b", "c")
//    if (-1 !in 0..list.lastIndex) {
//        println("-1 is out of range")
//    }
//    if (list.size !in list.indices) {
//        println("list size is out of valid list indices range too")
//    }

    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

fun lambda() {
    val fruits = listOf<String>("banana", "avocado", "apple", "kiwifruit")
    fruits.filter { it.startsWith("a") }.sortedBy { it }.map { it.toUpperCase() }.forEach { println(it) }
}

//inline fun <reified T: Any> Gson:fromJson(json: jsonElement): T = this.fromJson(json, T::class.java)

fun main(args: Array<String>) {

//    val files = File("Test").listFiles()
//    println(files?.size)

//    val files = File("Test").listFiles()
//    println(files?.size ?: "empty")

//    lambda()

//    range()

//    println(describle(123))

//    circle()

//    println("字符串长度${getStringLength("String")}")

//    printProduct("3", "4")

//    print("${maxOf(1, 2)}")

//    sum(1, 2)

//    print()
}

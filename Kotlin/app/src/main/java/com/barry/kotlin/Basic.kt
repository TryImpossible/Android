package com.barry.kotlin

fun variable() {
    val a: Int = 10000
    println("同一性: a === a -> ${a === a}")
    println("相等性: a == a -> ${a == a}")
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println("同一性: boxedA === anotherBoxedA -> ${boxedA === anotherBoxedA}")
    println("相等性: boxedA == anotherBoxedA -> ${boxedA == anotherBoxedA}")
}

fun transfer() {
    val b: Byte = 1
    val i: Int = b.toInt()
    val num: Int = 123
    println("i = $i")

    println("toByte() ${num.toByte()}")
    println("toShort() ${num.toShort()}")
    println("toInt() ${num.toInt()}")
    println("toLong() ${num.toLong()}")
    println("toFloat() ${num.toFloat()}")
    println("toDouble() ${num.toDouble()}")
    println("toChar() ${num.toChar()}")

    println(1L + 3)
}

fun calc() {
    val x = (1 shl 2) and 0x000FF00
    println(x)
}

fun array() {

//    val asc = arrayOf(1, 2, 3)
//    val asc = arrayOfNulls<String>(3)
    val asc = Array(5, { i -> (i * i).toString() })
    asc.forEach { println(it) }
    println()

    intArrayOf(1, 2, 3)
    byteArrayOf()
    shortArrayOf()
}

fun char() {
    val str: String = "String"
    for (c in str)
        println(c)

    println()

    val s = "abc" + 1
    println(s + "def")

    println()

    val greeting = "Hello, world!\n"
    println(greeting)

    println()

    val text = """
        for (c in "foo")
            print(c)
    """.trimIndent()
    println(text)

    println()

    val i = 10
    println("i = $i")

    println()

    val ss = "abc"
    println("ss.length is ${ss.length}")

    println()

    val price = """
        ${'$'}9.99
    """.trimIndent()
    println("price is $price")
}

fun control() {
    var a = 1
    var b = 2
    var max = if (a > b) a else b
    println("max is $max")


    val x = 11
    when(x) {
        0, 1 -> print("x == 0 or x == 1")
        2 -> print("x == 2")
        in 1..10 -> print("x is in the range")
        !in 10..20 -> print("x is outside the range")
        else -> {
            print("x is neither 1 nor 2")
        }
    }

    for (i in 1..3) {
        println(i)
    }
    for (i in 6 downTo 0 step 2) {
        println(i)
    }
    val array = arrayOf("a", "b", "c")
    for (i in array.indices) {
        println(array[i])
    }
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }

    var j = 3;
    while (j > 0) {
        println("--while--")
        j--
    }

    for (i in 1..3) {
        for (j in 1..10) {
            if ( j == 3) break
            println("j = $j")
        }
    }
}

fun foo1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 非局部直接返回到 foo() 的调用者
        println(it)
    }
    println("this point is unreachable")
}

fun foo2() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit
        println(it)
    }
    println(" done with explicit label ")
}

fun foo3() {
//    listOf(1, 2, 3, 4, 5).forEach {
//        if (it == 3) return@forEach
//        println(it)
//    }
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int){
        if (value == 3) return
        println(value)
    })
    println(" done with implicit label ")
}

fun foo4(){
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            println(it)
        }
    }
    println(" done with nested loop ")
}

fun main(args: Array<String>) {

//    foo4()

//    foo3()

//    foo2()

//    foo1()

//    control()

//    char()

//    array()

//    calc()

//    transfer()

//    variable()
}
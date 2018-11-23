package com.barry.kotlin

//class Person constructor(firstName: String) {
//
//}

class Person(firstName: String) {

}

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

class Customer(name: String) {
    val customerKey = name.toUpperCase()
}



fun main(args: Array<String>) {

    Customer("李四");
//    InitOrderDemo("张三")
}
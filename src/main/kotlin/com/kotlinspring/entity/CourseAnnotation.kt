@file:JvmName("CourseUtils")

package com.kotlinspring.entity

import com.kotlinplayground.CourseJava

data class CourseAnnotation @JvmOverloads
    constructor(
    val id: Int?,
    var name: String,
    var category: String = "category",
) {

    @JvmField
    var noOfCourses = 10

    companion object {
        fun printNameOtherNotStatic(name: String = "default") {
            println("name: $name")
        }

        @JvmStatic
        fun printNameOther(name: String = "default") {
            println("name: $name")
        }
    }
}

@JvmName("jvmAnnotationFunctionName")
@JvmOverloads
fun printName(name: String = "default"){
    println("printName function output: $name")
}

fun main() {
    val courseJava = CourseJava(2, "Example course", "A category")
    println("courseJava is $courseJava")

    courseJava.id = 4
    courseJava.name = "Name changed"

    println("updated course is $courseJava")
}
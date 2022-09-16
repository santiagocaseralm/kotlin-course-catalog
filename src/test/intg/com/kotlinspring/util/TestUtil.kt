package com.kotlinspring.util

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.entity.Course
import com.kotlinspring.entity.Instructor

fun courseEntityList() = listOf(
    Course(null, "Build RestFul APIs using SpringBoot and Kotlin", "Development"),
    Course(null, "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development"),
    Course(null, "Wiremock for Java Developers", "Development")
)

fun courseDTO (
    id: Int? = null,
    name: String = "Build RestFul APIs using Spring Boot and Kotlin",
    category: String = "George",
    //instructorId: Int? = 1
) = CourseDTO (
    id,
    name,
    category,
    //instructorId
        )

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(null,
    "Build RestFul APIs using SpringBoot and Kotlin", "Development",
    instructor),
    Course(null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development",
        instructor),
    Course(null,
        "Wiremock for Java Developers", "Development",
        instructor),
)

fun instructorEntity(name : String = "Juan Allende") = Instructor(null, name)

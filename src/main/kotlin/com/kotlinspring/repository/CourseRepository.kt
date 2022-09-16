package com.kotlinspring.repository

import com.kotlinspring.entity.Course
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: CrudRepository<Course, Int> {

    fun findByNameContaining(courseName: String): List<Course>

    @Query(value = "select * from Courses where name like %?1%", nativeQuery = true)
    fun findCoursesByName(courseName: String): List<Course>
}
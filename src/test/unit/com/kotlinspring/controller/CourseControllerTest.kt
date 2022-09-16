package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.entity.Course
import com.kotlinspring.service.CourseService
import com.kotlinspring.util.courseDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseServiceMock: CourseService

    @Test
    fun addCourse() {
        val courseDTO =  CourseDTO(null, "Example course name", "categoryCourse", 1)

        every {courseServiceMock.addCourse(any())} returns courseDTO(id = 1)

        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedCourseDTO!!.id != null
        }
    }

    @Test
    fun retrieveAllCourses() {
        every {courseServiceMock.retrieveAllCourses(any())}.returnsMany(
            listOf(courseDTO(id = 1),
            courseDTO(id = 2,
            name = "Build Reactive Microservices using Spring WebFlux/SpringBoot"))
        )
        val courseDTOs = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CourseDTO>()
            .returnResult()
            .responseBody

        Assertions.assertEquals(2, courseDTOs!!.size)
    }

    @Test
    fun updateCourse() {
        val course = Course (null, "Build RestFul APIs using SpringBoot and Kotlin", "Development")

        every {courseServiceMock.updateCourse(any(), any())} returns CourseDTO(100, "Build RestFul APIs using SpringBoot and Kotlin1", "Development")

        val updatedCourseDTO = CourseDTO (null, "Build RestFul APIs using SpringBoot and Kotlin1", "Development")

        val updatedCourse =  webTestClient
            .put()
            .uri("/v1/courses/{course_id}", 100)
            .bodyValue(updatedCourseDTO)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("Build RestFul APIs using SpringBoot and Kotlin1", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        every {courseServiceMock.deleteCourse(any())} just runs

        val updatedCourse =  webTestClient
            .delete()
            .uri("/v1/courses/{course_id}", 100)
            .exchange()
            .expectStatus().isNoContent
    }

    @Test
    fun addCourse_validation() {
        val courseDTO =  CourseDTO(null, "", "categoryCourse", 1)

        every {courseServiceMock.addCourse(any())} returns courseDTO(id = 1)

        val response = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("courseDTO.name must not be blank", response)
    }

    @Test
    fun addCourse_runtimeException() {
        val courseDTO =  CourseDTO(null, "Example course name", "categoryCourse", 1)

        val errorMessage = "Unexpected error occured"
        every {courseServiceMock.addCourse(any())} throws RuntimeException(errorMessage)

        val response = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().is5xxServerError
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(errorMessage, response)
    }
}
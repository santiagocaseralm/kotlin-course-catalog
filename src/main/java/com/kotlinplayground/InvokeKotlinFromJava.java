package com.kotlinplayground;

import com.kotlinspring.entity.Authenticate;
import com.kotlinspring.entity.Course;
import com.kotlinspring.entity.CourseAnnotation;
import com.kotlinspring.entity.CourseUtils;
import com.kotlinspring.entity.Instructor;

import java.util.ArrayList;

public class InvokeKotlinFromJava {

    public static void main(String[] args) {
        var course = new Course(1, "Build RestFul APIs using SpringBoot and Kotlin", "Development", new Instructor(1, "Juan", new ArrayList<Course>()));
        var courseAnnotation = new CourseAnnotation(1, "Build RestFul APIs using SpringBoot and Kotlin", "Development");
        var courseAnnotationOverload = new CourseAnnotation(1, "Build RestFul APIs using SpringBoot and Kotlin");

        System.out.println("course: " + course);
        System.out.println("courseAnnotation: " +courseAnnotation);
        System.out.println("courseAnnotation: " +courseAnnotationOverload);

        System.out.println("noOfCourses before update: " + courseAnnotation.noOfCourses);

        courseAnnotation.noOfCourses = 11;
        System.out.println("noOfCourses after update: " + courseAnnotation.noOfCourses);

        CourseUtils.jvmAnnotationFunctionName();
        CourseUtils.jvmAnnotationFunctionName("One text");

        CourseAnnotation.Companion.printNameOtherNotStatic("printNameOtherNotStatic");
        CourseAnnotation.printNameOther("acbdef");

        Authenticate.INSTANCE.authenticate("Juan", "juan");
        Authenticate.authenticate("Juan2", "juan2");
    }
}

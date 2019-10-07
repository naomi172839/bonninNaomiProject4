/*
 * Copyright (c) 2019
 * Author: Naomi Bonnin
 * Assignment: CMIS242 Project 4
 * Last Modified 10/7/19, 6:26 PM
 * Description: This is a simple database program with appropriate CRUD operations
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getMajor() {
        Student james = new Student("James","Biology");
        assertEquals("Biology",james.getMajor());
    }

    @Test
    void setMajor() {
        Student james = new Student("James","Chemistry");
        james.setMajor("Biology");
        assertEquals("Biology",james.getMajor());
    }

    @Test
    void getCreditHours() {
        Student james = new Student("James", "Biology");
        assertEquals(0, james.getCreditHours());
    }

    @Test
    void setCreditHours() {
        Student james = new Student("James", "Biology");
        james.setCreditHours(4);
        assertEquals(4, james.getCreditHours());
    }

    @Test
    void getGradePoints() {
        Student james = new Student("James", "Biology");
        assertEquals(0, james.getGradePoints());
    }

    @Test
    void setGradePoints() {
        Student james = new Student("James", "Biology");
        james.setGradePoints(4);
        assertEquals(4, james.getGradePoints());
    }

    @Test
    void getName() {
        Student james = new Student("James", "Biology");
        assertEquals("James", james.getName());
    }

    @Test
    void setName() {
        Student james = new Student("James", "Biology");
        james.setName("Mark");
        assertEquals("Mark", james.getName());
    }

    @Test
    void courseCompleted() {
        Student james = new Student("James","Chemistry");
        james.courseCompleted(4,4);
        assertEquals(16.00, james.getGradePoints());
    }

    @Test
    void getGPA() {
        Student james = new Student("James","Chemistry");
        james.courseCompleted(4,4);
        assertEquals("4.0", james.getGPA());
    }

    @Test
    void testToString() {
        Student james = new Student("James","Chemistry");
        assertEquals("Name: James, Major: Chemistry, GPA: 4.00", james.toString());
    }
}
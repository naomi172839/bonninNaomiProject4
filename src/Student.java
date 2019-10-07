/*
 * Copyright (c) 2019
 * Author: Naomi Bonnin
 * Assignment: CMIS242 Project 4
 * Last Modified 10/7/19, 6:24 PM
 * Description: This is a simple database program with appropriate CRUD operations
 */

/*
The student class defines the student record.  It contains 4 variables that are used to store the students name, major
total credit hours and total quality points.  The students current GPA is not stored but rather calculated as needed
 */
class Student {

    //Variables that store the student details
    private String name, major;
    private int creditHours, gradePoints;

    //The constructor for the Student class.  It accepts 2 arguments, a String for the name, and a String for the major
    //It also initilizes the creditHours and gradePoints to 0
    Student(String name, String major) {
        this.name = name;
        this.major = major;
        creditHours = gradePoints = 0;
    }

    //Default getter for major
    public String getMajor() {
        return major;
    }

    //Default setter for major
    public void setMajor(String major) {
        this.major = major;
    }

    //Default getter for creditHours
    public int getCreditHours() {
        return creditHours;
    }

    //Default setter for creditHours
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    //Getter for gradePoints.  Note that the value is cast as a Double since it is possible to have a non
    //integer GPA
    public Double getGradePoints() {
        return (double) gradePoints;
    }

    //Default setter for gradePoints
    public void setGradePoints(int gradePoints) {
        this.gradePoints = gradePoints;
    }

    //Default getter for name
    public String getName() {
        return name;
    }

    //Default setter for name
    public void setName(String name) {
        this.name = name;
    }

    //The course completed method updates the student record for a completed course.  It accepts 2 arguments, a int for
    //the quality points and an int for the credits received.  It has no return value
    void courseCompleted(int grade, int credits) {
        //Sets the grade points using the getters and setters as well as the supplied values.  Note that getGradePoints
        //is cast as an int thanks to Java being strongly typed
        setGradePoints((int) (getGradePoints() + (grade * credits)));
        //Sets the credit hours based on the supplied value and uses the getters and setters
        setCreditHours(getCreditHours() + credits);
    }

    //A helper method that calculates the GPA when called.  Returns a string with the GPA since it is only used in the
    //toString method below
    public String getGPA() {
        if (getCreditHours() == 0) {
            return "4.00";
        }
        //Ensures that the string is formated to 2 decimal places.
        return String.format("%.2f" ,getGradePoints() / getCreditHours());
    }

    //An overriden toString method that returns the student record in a custom format
    @Override
    public String toString() {
        return "Name: " + getName() + ", Major: " + getMajor() + ", GPA: " + getGPA();
    }
}


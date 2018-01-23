package com.example.sapir.shapeit;

/**
 * this class creates a calendar object.
 * this object accepts two parameters: Lesson Name and Lesson Time.
 */


public class Calendar {
    /**
     * attributes of class.
     */

    public String lesson_name, hour;

    /**
     *  constructor
     * @param lesson_name
     * @param hour
     */
    public Calendar(String lesson_name, String hour) {
        this.lesson_name = lesson_name;
        this.hour = hour;

    }


}


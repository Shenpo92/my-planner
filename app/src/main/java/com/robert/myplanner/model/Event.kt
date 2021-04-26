package com.robert.myplanner.model


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class Event(val title: String, val start: String, val end: String) {

    // This function allows us to retrieve a Date object from the start string
    // which helps us sort the list of event by chronological order
    fun getStartDate(): LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a")
        return LocalDateTime.parse(start, formatter);
    }

    fun getEndDate(): LocalDateTime{
        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a")
        return LocalDateTime.parse(end, formatter);
    }
}
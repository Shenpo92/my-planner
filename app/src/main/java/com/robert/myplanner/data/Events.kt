package com.robert.myplanner

import android.content.res.Resources

import android.content.res.AssetManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.robert.myplanner.model.Event

fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText() }

fun getInitialEventList(resources: Resources): List<Event> {
    val jsonString = resources.assets.readFile("mock-data.json")
    val eventList : List<Event> =  GsonBuilder().create().fromJson(jsonString, object : TypeToken<List<Event>>() {}.type)

    // Here we sort the list of event by chronological order before returning it to the viewmodel
    return eventList.sortedBy {
        it.getStartDate()
    }
}



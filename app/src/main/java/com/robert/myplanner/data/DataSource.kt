package com.robert.myplanner.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robert.myplanner.model.Event
import com.robert.myplanner.getInitialEventList

class DataSource(resources: Resources) {
    private val initialEventList = getInitialEventList(resources)
    private val eventLiveData = MutableLiveData<List<Event>>()

    init {
        eventLiveData.value = initialEventList
    }

    fun getEventList() : LiveData<List<Event>> {
        return eventLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
package com.robert.myplanner.eventList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robert.myplanner.data.DataSource

class EventListViewModel(val dataSource: DataSource) : ViewModel() {
    val eventLiveData = dataSource.getEventList()
}

// The factory helps us fire up only one instance of the view model.
class EventListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
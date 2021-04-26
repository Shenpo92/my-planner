package com.robert.myplanner.eventDetail



    import android.content.Context
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import com.robert.myplanner.data.DataSource
    import com.robert.myplanner.model.Event


class EventDetailViewModel(private val dataSource: DataSource) : ViewModel() {

        fun getEventDetails(title: String?) :  Event? {
            return dataSource.getEventList().value?.find { it.title == title };
        }
    }

// The factory helps us fire up only one instance of the view model.
    class EventDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventDetailViewModel(
                        dataSource = DataSource.getDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
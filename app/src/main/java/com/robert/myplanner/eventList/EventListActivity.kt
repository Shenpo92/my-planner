package com.robert.myplanner.eventList

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.robert.myplanner.*
import com.robert.myplanner.eventDetail.EventDetailActivity
import com.robert.myplanner.model.Event
import kotlinx.android.synthetic.main.activity_main.*

// Ideally we would use a Unique Identifier here instead of the event title
// in order to prevent case where titles are not unique
const val EVENT_TITLE = "event title"

class EventListActivity : AppCompatActivity() {
    private val eventListViewModel by viewModels<EventListViewModel>{
        EventListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val eventAdapter = EventAdapter { event -> adapterOnClick(event)}
        val layoutManager = LinearLayoutManager(this)

        recyleView_events.layoutManager = layoutManager
        recyleView_events.adapter = eventAdapter

        eventListViewModel.eventLiveData.observe(this, {
            it.let {
                eventAdapter.submitList(it as MutableList<Event>)
            }
        })
    }

    private fun adapterOnClick(event: Event) {
        val intent = Intent(this, EventDetailActivity()::class.java)

        // Passing the event title so we can search through the list of events and match the correct event
        intent.putExtra(EVENT_TITLE, event.title)

        startActivity(intent)
    }
}
package com.robert.myplanner.eventDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.robert.myplanner.R
import com.robert.myplanner.eventList.EVENT_TITLE
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {
    private val eventDetailViewModel by viewModels<EventDetailViewModel> {
        EventDetailViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        var currentEventTitle : String? = null
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentEventTitle = bundle.getString(EVENT_TITLE)
        }

        val currentEvent = eventDetailViewModel.getEventDetails(currentEventTitle)
        title_detail_textView.text = currentEvent?.title
        start_detail_textView.text = "Start Date: ${currentEvent?.start}"
        end_detail_textView.text = "End Date: ${currentEvent?.end}"

    }
}
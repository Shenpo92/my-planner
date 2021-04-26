package com.robert.myplanner.eventList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.robert.myplanner.R
import com.robert.myplanner.model.Event

class EventAdapter(private val onClick: (Event) -> Unit) :
        ListAdapter<Event, EventAdapter.EventViewHolder>(EventDiffCallback) {

    class EventViewHolder(itemView: View, val onClick: (Event) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val eventTitleTextView: TextView = itemView.findViewById(R.id.title_textView)
        private val eventStartTextView: TextView = itemView.findViewById(R.id.start_textView)
        private val eventEndTextView: TextView = itemView.findViewById(R.id.end_textView)
        private val warningView: ImageView = itemView.findViewById(R.id.warning_view)

        private var currentEvent: Event? = null

        init {
            itemView.setOnClickListener {
                currentEvent?.let {
                    onClick(it)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(event: Event, previousEvent: Event?){
            currentEvent = event
            if(event.title == null || event.title.isEmpty()){
                eventTitleTextView.text = "No title for this event  :("
            } else {
                eventTitleTextView.text = event.title
            }
            eventStartTextView.text = "Starts: ${event.start}"
            eventEndTextView.text = "Ends: ${event.end}"
            if(previousEvent != null){
                if(event.getStartDate().isBefore(previousEvent.getEndDate())){
                    //CASE: Current event START date of is before previous event END date
                    warningView.visibility = View.VISIBLE
                } else if (event.getStartDate().isAfter(event.getEndDate())){
                    // CASE: Current event START date is after current event END date
                    warningView.visibility = View.VISIBLE
                } else if (event.getStartDate() == null || event.getEndDate() == null){
                    // CASE: One of the date is NULL
                    warningView.visibility = View.VISIBLE
                } else {
                    warningView.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        var previousEvent: Event? = null
        previousEvent = if (position > 0) {
            getItem(position -1)
        } else {
            null
        }
        holder.bind(event, previousEvent)
    }
}

object EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.title == newItem.title
    }
}
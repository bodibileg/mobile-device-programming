package com.example.thesportnewsandinformation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thesportnewsandinformation.data.Events
import com.example.thesportnewsandinformation.databinding.EventsBinding

class EventsAdapter(private val context: Context, private var events: ArrayList<Events>) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    class EventViewHolder(val binding: EventsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.binding.eventName.text = event.name
        holder.binding.eventDescription.text = event.description
        holder.binding.eventDate.text = event.date
    }

    override fun getItemCount(): Int = events.size
}

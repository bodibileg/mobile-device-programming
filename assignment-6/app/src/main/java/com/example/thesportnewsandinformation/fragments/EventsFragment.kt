package com.example.thesportnewsandinformation.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesportnewsandinformation.GlobalData
import com.example.thesportnewsandinformation.R
import com.example.thesportnewsandinformation.adapter.EventsAdapter
import com.example.thesportnewsandinformation.data.Athletes
import com.example.thesportnewsandinformation.data.Events
import com.example.thesportnewsandinformation.databinding.FragmentEventsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EventsFragment : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private lateinit var events: ArrayList<Events>
    private lateinit var globalData: GlobalData

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalData = GlobalData(requireContext())
        events = globalData.getEvents()

        val eventsAdapter = EventsAdapter(view.context, events)
        binding.eventsView.adapter = eventsAdapter
        binding.eventsView.layoutManager = LinearLayoutManager(view.context)

        val addButton: FloatingActionButton = binding.addEvents
        addButton.setOnClickListener {
            showAddEventDialog()
        }
    }

    private fun showAddEventDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.events_dialog, null)
        val addEventName = dialogView.findViewById<EditText>(R.id.add_event_name)
        val addEventDescription = dialogView.findViewById<EditText>(R.id.add_event_description)
        val addEventDate = dialogView.findViewById<EditText>(R.id.add_event_date)

        addEventDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                addEventDate.setText("${selectedMonth + 1}/${selectedDayOfMonth}/${selectedYear}")
            }, year, month, day)

            datePickerDialog.show()
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Add New Event")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val eventName = addEventName.text.toString()
                val eventDescription = addEventDescription.text.toString()
                val eventDate = addEventDate.text.toString()

                val newEvent = Events(eventName, eventDate, eventDescription)
                events.add(newEvent)
                globalData.setEvents(events)
                Toast.makeText(this.context, "Event added successfully", Toast.LENGTH_SHORT).show()
                binding.eventsView.adapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}
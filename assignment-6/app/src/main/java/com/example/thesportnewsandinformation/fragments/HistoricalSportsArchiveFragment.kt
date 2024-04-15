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
import com.example.thesportnewsandinformation.adapter.HistoricalArchivesAdapter
import com.example.thesportnewsandinformation.data.Events
import com.example.thesportnewsandinformation.data.HistoricalArchive
import com.example.thesportnewsandinformation.databinding.FragmentHistoricalSportsArchiveBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

class HistoricalSportsArchiveFragment : Fragment() {
    private var _binding: FragmentHistoricalSportsArchiveBinding? = null
    private lateinit var historicalArchives : ArrayList<HistoricalArchive>
    private lateinit var globalData: GlobalData
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHistoricalSportsArchiveBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalData = GlobalData(requireContext())
        historicalArchives = globalData.getHistoricalArchives()

        val historicalArchivesAdapter = HistoricalArchivesAdapter(view.context, historicalArchives)
        binding.historicalArchivesView.adapter = historicalArchivesAdapter
        binding.historicalArchivesView.layoutManager = LinearLayoutManager(view.context)

        val addButton: FloatingActionButton = binding.addHistoricalArchives
        addButton.setOnClickListener {
            showAddHistoricalArchiveDialog()
        }
    }

    private fun showAddHistoricalArchiveDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.events_dialog, null)
        val addHistoricalArchiveName = dialogView.findViewById<EditText>(R.id.add_event_name)
        val addHistoricalArchiveDescription = dialogView.findViewById<EditText>(R.id.add_event_description)
        val addHistoricalArchiveDate = dialogView.findViewById<EditText>(R.id.add_event_date)

        addHistoricalArchiveDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                addHistoricalArchiveDate.setText("${selectedMonth + 1}/${selectedDayOfMonth}/${selectedYear}")
            }, year, month, day)

            datePickerDialog.show()
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Add New Historical Archive")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val historicalArchiveName = addHistoricalArchiveName.text.toString()
                val historicalArchiveDescription = addHistoricalArchiveDescription.text.toString()
                val historicalArchiveDate = addHistoricalArchiveDate.text.toString()

                val newArchive = HistoricalArchive(historicalArchiveName, historicalArchiveDate, historicalArchiveDescription)
                historicalArchives.add(newArchive)
                globalData.setHistoricalArchives(historicalArchives)
                Toast.makeText(this.context, "Archive added successfully", Toast.LENGTH_SHORT).show()
                binding.historicalArchivesView.adapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
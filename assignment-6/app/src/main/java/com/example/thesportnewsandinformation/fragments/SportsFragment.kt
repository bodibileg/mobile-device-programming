package com.example.thesportnewsandinformation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesportnewsandinformation.GlobalData
import com.example.thesportnewsandinformation.R
import com.example.thesportnewsandinformation.adapter.SportsAdapter
import com.example.thesportnewsandinformation.data.Sports
import com.example.thesportnewsandinformation.databinding.FragmentSportsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SportsFragment : Fragment() {

    private var _binding: FragmentSportsBinding? = null
    private lateinit var sports: ArrayList<Sports>
    private lateinit var globalData: GlobalData
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSportsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalData = GlobalData(requireContext())
        sports = globalData.getSports()

        val sportsAdapter = SportsAdapter(view.context, sports)
        binding.sportsView.adapter = sportsAdapter
        binding.sportsView.layoutManager = GridLayoutManager(view.context, 2)

        val addButton: FloatingActionButton = binding.addSport
        addButton.setOnClickListener {
            showAddSportDialog()
        }
    }

    private fun showAddSportDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.sports_dialog, null)
        val spinnerSportType = dialogView.findViewById<Spinner>(R.id.add_sport_type_spinner)
        val sportTypes = resources.getStringArray(R.array.sport_types)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sportTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSportType.adapter = adapter
        val editSportName = dialogView.findViewById<EditText>(R.id.add_sport_name)
        val editSportInstruction = dialogView.findViewById<EditText>(R.id.add_sport_instruction)

        AlertDialog.Builder(requireContext())
            .setTitle("Add New Sport")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val sportName = editSportName.text.toString()
                val sportInstruction = editSportInstruction.text.toString()
                val sportType = spinnerSportType.selectedItem.toString()

                val newSport = Sports(sportName, sportInstruction, sportType)
                sports.add(newSport)
                globalData.setSports(sports)
                Toast.makeText(this.context, "Sport added successfully", Toast.LENGTH_SHORT).show()
                binding.sportsView.adapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
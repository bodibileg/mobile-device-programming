package com.example.thesportnewsandinformation.fragments

import android.app.AlertDialog
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
import com.example.thesportnewsandinformation.adapter.AthletesAdapter
import com.example.thesportnewsandinformation.data.Athletes
import com.example.thesportnewsandinformation.databinding.FragmentAthletesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AthletesFragment : Fragment() {

    private var _binding: FragmentAthletesBinding? = null
    private lateinit var athletes: ArrayList<Athletes>
    private lateinit var globalData: GlobalData
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAthletesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalData = GlobalData(requireContext())
        athletes = globalData.getAthletes()

        val athletesAdapter = AthletesAdapter(view.context, athletes)
        binding.athletesView.adapter = athletesAdapter
        binding.athletesView.layoutManager = LinearLayoutManager(view.context)

        val addButton: FloatingActionButton = binding.addAthletes
        addButton.setOnClickListener {
            showAddAthleteDialog()
        }
    }

    private fun showAddAthleteDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.athletes_dialog, null)
        val addAthleteName = dialogView.findViewById<EditText>(R.id.add_athlete_name)
        val addAthleteSport = dialogView.findViewById<EditText>(R.id.add_athlete_sport)
        val addAthleteCountry = dialogView.findViewById<EditText>(R.id.add_athlete_country)
        val addAthletePersonalBest = dialogView.findViewById<EditText>(R.id.add_athlete_personal_best)
        val addAthleteNumberOfMedals = dialogView.findViewById<EditText>(R.id.add_athlete_number_of_medals)
        val addAthleteInterestingFacts = dialogView.findViewById<EditText>(R.id.add_athlete_interesting_facts)

        AlertDialog.Builder(requireContext())
            .setTitle("Add New Athlete")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val athleteName = addAthleteName.text.toString()
                val athleteSport = addAthleteSport.text.toString()
                val athleteCountry = addAthleteCountry.text.toString()
                val athletePersonalBest = addAthletePersonalBest.text.toString()
                val athleteNumberOfMedals = addAthleteNumberOfMedals.text.toString().toIntOrNull() ?: 0
                val athleteInterestingFacts = addAthleteInterestingFacts.text.toString()

                val newAthlete = Athletes(athleteName, athleteSport, athleteCountry, athletePersonalBest, athleteNumberOfMedals, athleteInterestingFacts)
                athletes.add(newAthlete)
                globalData.setAthletes(athletes)
                Toast.makeText(this.context, "Athlete added successfully", Toast.LENGTH_SHORT).show()
                binding.athletesView.adapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
package com.example.animalkingdomexplorer.ui.speciesdetails

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.databinding.FragmentAnimalDetailsBinding
import com.example.animalkingdomexplorer.databinding.FragmentSpeciesDetailsBinding
import com.example.animalkingdomexplorer.ui.animaldetails.AnimalAdapter
import com.example.animalkingdomexplorer.ui.animaldetails.AnimalViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SpeciesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSpeciesDetailsBinding
    private lateinit var speciesViewModel: SpeciesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpeciesDetailsBinding.inflate(inflater, container, false)
        val addButton: FloatingActionButton = binding.addSpecies
        addButton.setOnClickListener {
            showAddSpeciesDialog()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.speciesView.layoutManager = LinearLayoutManager(requireContext())
        speciesViewModel = ViewModelProvider(this)[SpeciesViewModel::class.java]

        speciesViewModel.allSpecies.observe(viewLifecycleOwner) { species ->
            binding.speciesView.adapter = SpeciesAdapter(species)
        }
    }


    private fun showAddSpeciesDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.add_species_dialog, null)
        val addSpeciesName = dialogView.findViewById<EditText>(R.id.add_species_name)
        val addSpeciesDescription = dialogView.findViewById<EditText>(R.id.add_description)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Species")
            .setView(dialogView)
            .setPositiveButton("Save", null)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.setOnShowListener {
            val saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            saveButton.setOnClickListener {
                val speciesName = addSpeciesName.text.toString().trim()
                val speciesDescription = addSpeciesDescription.text.toString().trim()

                if (validateFields(
                        speciesName,
                        speciesDescription,
                        addSpeciesName,
                        addSpeciesDescription
                    )
                ) {
                    val newSpecies = Species(0, speciesName, speciesDescription)
                    speciesViewModel.insert(newSpecies)

                    Toast.makeText(
                        requireContext(),
                        "Species added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }

    private fun validateFields(
        name: String,
        description: String,
        nameEditText: EditText,
        descriptionEditText: EditText
    ): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            nameEditText.error = "Name is required"
            nameEditText.requestFocus()
            isValid = false
        }

        if (description.isEmpty()) {
            descriptionEditText.error = "Description is required"
            descriptionEditText.requestFocus()
            isValid = false
        }

        return isValid
    }
}
package com.example.animalkingdomexplorer.ui.animaldetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.databinding.FragmentAnimalDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnimalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAnimalDetailsBinding
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalDetailsBinding.inflate(inflater, container, false)
        val addButton: FloatingActionButton = binding.addAnimal
        addButton.setOnClickListener {
            showAddAnimalDialog()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.animalsView.layoutManager = LinearLayoutManager(requireContext())
        animalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        animalViewModel.allAnimals.observe(viewLifecycleOwner) { animals ->
            binding.animalsView.adapter = AnimalAdapter(animals)
        }
    }


    private fun showAddAnimalDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.add_animal_dialog, null)
        val addAnimalName = dialogView.findViewById<EditText>(R.id.add_animal_name)
        val addHabitat = dialogView.findViewById<EditText>(R.id.add_habitat)
        val addDiet = dialogView.findViewById<EditText>(R.id.add_diet)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Animal")
            .setView(dialogView)
            .setPositiveButton("Save", null) // We set onClickListener to null for now
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.setOnShowListener { // Setting validation and action on positive button click here
            val saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            saveButton.setOnClickListener {
                val animalName = addAnimalName.text.toString().trim()
                val animalHabitat = addHabitat.text.toString().trim()
                val animalDiet = addDiet.text.toString().trim()

                if (validateAnimalFields(animalName, animalHabitat, animalDiet, addAnimalName, addHabitat, addDiet)) {
                    val newAnimal = Animal(0, animalName, animalHabitat, animalDiet)
                    animalViewModel.insert(newAnimal)

                    Toast.makeText(
                        requireContext(),
                        "Animal added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }

    private fun validateAnimalFields(name: String, habitat: String, diet: String, nameEditText: EditText, habitatEditText: EditText, dietEditText: EditText): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            nameEditText.error = "Name is required"
            nameEditText.requestFocus()
            isValid = false
        }

        if (habitat.isEmpty()) {
            habitatEditText.error = "Habitat is required"
            habitatEditText.requestFocus()
            isValid = false
        }

        if (diet.isEmpty()) {
            dietEditText.error = "Diet is required"
            dietEditText.requestFocus()
            isValid = false
        }

        return isValid
    }

}
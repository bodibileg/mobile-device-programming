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

        AlertDialog.Builder(requireContext())
            .setTitle("Add Animal")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val animalName = addAnimalName.text.toString()
                val animalHabitat = addHabitat.text.toString()
                val animalDiet = addDiet.text.toString()

                val newAnimal = Animal(0, animalName, animalHabitat, animalDiet)
                animalViewModel.insert(newAnimal)

                animalViewModel.allAnimals.observe(viewLifecycleOwner) { animals ->
                    binding.animalsView.adapter = AnimalAdapter(animals)
                }
                Toast.makeText(this.context, "Animal added successfully", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
package com.example.animalkingdomexplorer.ui.animaldetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal

class AnimalAdapter(private val animals: List<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AnimalAdapter.AnimalViewHolder {
        return AnimalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.animal_item, parent, false)
        )
    }

    override fun getItemCount() = animals.size
    override fun onBindViewHolder(holder: AnimalAdapter.AnimalViewHolder, position: Int) {

        val animal = animals[position]
        holder.animalNameTextView.text = animal.name
        holder.animalHabitatTextView.text = animal.habitat
        holder.animalDietTextView.text = animal.diet
    }

    class AnimalViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val animalNameTextView: TextView = view.findViewById(R.id.animal_name)
        val animalHabitatTextView: TextView = view.findViewById(R.id.animal_habitat)
        val animalDietTextView: TextView = view.findViewById(R.id.animal_diet)

    }
}
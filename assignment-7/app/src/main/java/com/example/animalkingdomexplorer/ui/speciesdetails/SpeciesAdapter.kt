package com.example.animalkingdomexplorer.ui.speciesdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Species

class SpeciesAdapter(private val species: List<Species>) :
    RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            SpeciesAdapter.SpeciesViewHolder {
        return SpeciesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.species_item, parent, false)
        )
    }

    override fun getItemCount() = species.size
    override fun onBindViewHolder(holder: SpeciesAdapter.SpeciesViewHolder, position: Int) {

        val current = species[position]
        holder.speciesNameTextView.text = current.name
        holder.speciesDescriptionTextView.text = current.description
    }

    class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val speciesNameTextView: TextView = view.findViewById(R.id.species_name)
        val speciesDescriptionTextView: TextView = view.findViewById(R.id.species_description)

    }
}
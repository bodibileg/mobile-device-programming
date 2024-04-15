package com.example.thesportnewsandinformation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thesportnewsandinformation.data.Athletes
import com.example.thesportnewsandinformation.databinding.AthletesBinding

class AthletesAdapter(private val context: Context, private var athletes: ArrayList<Athletes>) :
    RecyclerView.Adapter<AthletesAdapter.AthleteViewHolder>() {

    class AthleteViewHolder(val binding: AthletesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthleteViewHolder {
        val binding = AthletesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AthleteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AthleteViewHolder, position: Int) {
        val athlete = athletes[position]
        holder.binding.athleteName.text = "Name: " + athlete.name
        holder.binding.athleteCountry.text = "Country: " + athlete.country
        holder.binding.athleteSport.text = "Sport: " + athlete.sport
        holder.binding.athletePersonalBest.text = "Personal Best: " + athlete.personalBest
        holder.binding.athleteNumberOfMedals.text = "Number of Medals: " + athlete.numberOfMedals.toString()
        holder.binding.athleteInterestingFacts.text = "Interesting Fact: " + athlete.interestingFacts
    }

    override fun getItemCount(): Int = athletes.size
}

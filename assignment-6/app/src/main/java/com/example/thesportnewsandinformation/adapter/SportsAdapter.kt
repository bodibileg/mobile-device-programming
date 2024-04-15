package com.example.thesportnewsandinformation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thesportnewsandinformation.data.Sports
import com.example.thesportnewsandinformation.databinding.SportsBinding

class SportsAdapter(private val context: Context, private var sports: ArrayList<Sports>) :
    RecyclerView.Adapter<SportsAdapter.SportViewHolder>() {

    class SportViewHolder(val binding: SportsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val binding = SportsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = sports[position]
        holder.binding.sportName.text = sport.name
        holder.binding.sportType.text = sport.type
        holder.binding.sportInstruction.text = sport.instructions
    }

    override fun getItemCount(): Int = sports.size
}
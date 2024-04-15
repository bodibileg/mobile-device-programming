package com.example.thesportnewsandinformation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thesportnewsandinformation.data.HistoricalArchive
import com.example.thesportnewsandinformation.databinding.HistoricalArchiveBinding

class HistoricalArchivesAdapter(
    private val context: Context, private var historicalArchives: ArrayList<HistoricalArchive>
) : RecyclerView.Adapter<HistoricalArchivesAdapter.HistoricalArchivesViewHolder>() {

    class HistoricalArchivesViewHolder(val binding: HistoricalArchiveBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): HistoricalArchivesViewHolder {
        val binding =
            HistoricalArchiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoricalArchivesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricalArchivesViewHolder, position: Int) {
        val historicalArchive = historicalArchives[position]
        holder.binding.historicalArchiveName.text = historicalArchive.name
        holder.binding.historicalArchiveDescription.text = historicalArchive.description
        holder.binding.historicalArchiveDate.text = historicalArchive.date
    }

    override fun getItemCount(): Int = historicalArchives.size
}

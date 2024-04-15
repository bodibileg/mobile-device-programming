package com.example.thesportnewsandinformation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thesportnewsandinformation.data.News
import com.example.thesportnewsandinformation.databinding.NewsBinding

class NewsAdapter(private val context: Context, private var news: ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: NewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = news[position]
        holder.binding.newsTitle.text = newsItem.title
        holder.binding.newsDescription.text = newsItem.description
        Glide.with(context)
            .load(newsItem.imageUrl)
            .into(holder.binding.newsImage)
    }

    override fun getItemCount(): Int = news.size
}

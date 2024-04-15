package com.example.thesportnewsandinformation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thesportnewsandinformation.GlobalData
import com.example.thesportnewsandinformation.R
import com.example.thesportnewsandinformation.adapter.EventsAdapter
import com.example.thesportnewsandinformation.adapter.NewsAdapter
import com.example.thesportnewsandinformation.data.News
import com.example.thesportnewsandinformation.data.Sports
import com.example.thesportnewsandinformation.databinding.FragmentNewsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private lateinit var news: ArrayList<News>
    private lateinit var globalData: GlobalData
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        globalData = GlobalData(requireContext())
        news = globalData.getNews()

        val newsAdapter = NewsAdapter(view.context, news)
        binding.newsView.adapter = newsAdapter
        binding.newsView.layoutManager = LinearLayoutManager(view.context)

        val addButton: FloatingActionButton = binding.addNews
        addButton.setOnClickListener {
            showAddNewsDialog()
        }
    }

    private fun showAddNewsDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.news_dialog, null)
        val addNewsTitle = dialogView.findViewById<EditText>(R.id.add_news_title)
        val addNewsImageUrl = dialogView.findViewById<EditText>(R.id.add_news_image_url)
        val addNewsDescription = dialogView.findViewById<EditText>(R.id.add_news_description)

        AlertDialog.Builder(requireContext())
            .setTitle("Add New News")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val newsTitle = addNewsTitle.text.toString()
                val newsDescription = addNewsDescription.text.toString()
                val newsImageUrl = addNewsImageUrl.text.toString()

                val newNews = News(newsTitle, newsImageUrl, newsDescription)
                news.add(newNews)
                globalData.setNews(news)
                Toast.makeText(this.context, "News added successfully", Toast.LENGTH_SHORT).show()
                binding.newsView.adapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
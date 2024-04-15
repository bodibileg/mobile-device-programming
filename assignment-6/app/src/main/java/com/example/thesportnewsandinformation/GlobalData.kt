package com.example.thesportnewsandinformation

import android.content.Context
import com.example.thesportnewsandinformation.data.Athletes
import com.example.thesportnewsandinformation.data.Events
import com.example.thesportnewsandinformation.data.HistoricalArchive
import com.example.thesportnewsandinformation.data.News
import com.example.thesportnewsandinformation.data.Sports
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GlobalData(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getSports(): ArrayList<Sports> {
        val json = sharedPreferences.getString("sports", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<Sports>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
    fun setSports(sports: ArrayList<Sports>) {
        val json = gson.toJson(sports)
        sharedPreferences.edit().putString("sports", json).apply()
    }
    fun getNews(): ArrayList<News> {
        val json = sharedPreferences.getString("news", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<News>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
    fun setNews(news: ArrayList<News>) {
        val json = gson.toJson(news)
        sharedPreferences.edit().putString("news", json).apply()
    }
    fun getAthletes(): ArrayList<Athletes> {
        val json = sharedPreferences.getString("athletes", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<Athletes>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
    fun setAthletes(athletes: ArrayList<Athletes>) {
        val json = gson.toJson(athletes)
        sharedPreferences.edit().putString("athletes", json).apply()
    }
    fun getEvents(): ArrayList<Events> {
        val json = sharedPreferences.getString("events", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<Events>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
    fun setEvents(events: ArrayList<Events>) {
        val json = gson.toJson(events)
        sharedPreferences.edit().putString("events", json).apply()
    }
    fun getHistoricalArchives(): ArrayList<HistoricalArchive> {
        val json = sharedPreferences.getString("historicalArchives", null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<HistoricalArchive>>() {}.type
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }
    fun setHistoricalArchives(historicalArchive: ArrayList<HistoricalArchive>) {
        val json = gson.toJson(historicalArchive)
        sharedPreferences.edit().putString("historicalArchives", json).apply()
    }

    fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }
}
package com.example.animalkingdomexplorer.data.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.AnimalDao
import com.example.animalkingdomexplorer.data.dao.SpeciesDao
import com.example.animalkingdomexplorer.data.database.AppDatabase
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species
import kotlinx.coroutines.flow.Flow

class SpeciesRepository(application: Application) {
    private val speciesDao: SpeciesDao
    val allSpecies: LiveData<List<Species>>

    init {
        val db = AppDatabase.getDatabase(application)
        speciesDao = db.speciesDao()
        allSpecies = speciesDao.getAllSpecies()
    }

    suspend fun insert(species: Species){
        speciesDao.addSpecies(species)
    }
}
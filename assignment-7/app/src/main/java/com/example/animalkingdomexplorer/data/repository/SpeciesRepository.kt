package com.example.animalkingdomexplorer.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.SpeciesDao
import com.example.animalkingdomexplorer.data.model.Species
import kotlinx.coroutines.flow.Flow

class SpeciesRepository(private val speciesDao: SpeciesDao) {

    val allSpecies: LiveData<List<Species>> = speciesDao.getAllSpecies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(species: Species) {
        speciesDao.addSpecies(species)
    }
}
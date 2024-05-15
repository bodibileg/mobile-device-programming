package com.example.animalkingdomexplorer.ui.speciesdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.data.repository.SpeciesRepository
import kotlinx.coroutines.launch

class SpeciesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SpeciesRepository
    val allSpecies: LiveData<List<Species>>

    init {
        repository = SpeciesRepository(application)
        allSpecies = repository.allSpecies
    }

    fun insert(species: Species) = viewModelScope.launch {
        repository.insert(species)
    }
}
package com.example.animalkingdomexplorer.ui.speciesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.data.repository.SpeciesRepository
import kotlinx.coroutines.launch

class SpeciesViewModel(private val repository: SpeciesRepository) : ViewModel() {
    val allSpecies: LiveData<List<Species>> = repository.allSpecies

    fun insert(species: Species) = viewModelScope.launch {
        repository.insert(species)
    }
}

class SpeciesViewModelFactory(private val repository: SpeciesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpeciesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.animalkingdomexplorer.ui.animaldetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AnimalRepository
    val allAnimals: LiveData<List<Animal>>

    init {
        repository = AnimalRepository(application)
        allAnimals = repository.allAnimals
    }

    fun insert(animal: Animal) = viewModelScope.launch {
        repository.insert(animal)
        Log.d("AnimalViewModel", "Animal inserted, fetching updated list...")
    }
}
package com.example.animalkingdomexplorer.data.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.AnimalDao
import com.example.animalkingdomexplorer.data.database.AppDatabase
import com.example.animalkingdomexplorer.data.model.Animal
import kotlinx.coroutines.flow.Flow

class AnimalRepository(application: Application) {
    private val animalDao: AnimalDao
    val allAnimals: LiveData<List<Animal>>

    init {
        val db = AppDatabase.getDatabase(application)
        animalDao = db.animalDao()
        allAnimals = animalDao.getAllAnimals()
    }

    suspend fun insert(animal: Animal){
        animalDao.addAnimal(animal)
    }
}
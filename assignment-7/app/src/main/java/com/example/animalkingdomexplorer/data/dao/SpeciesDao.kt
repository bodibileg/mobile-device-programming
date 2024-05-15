package com.example.animalkingdomexplorer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorer.data.model.Species
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {

    @Insert
    suspend fun addSpecies(species: Species)

    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>
    @Query("SELECT * FROM species WHERE id = :speciesId")
    fun getSpeciesById(speciesId: Long): LiveData<Species>
}
package lnr.hk.no.labbisv3

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class AnimalRepository(private val animalDao: AnimalDao){

    val allAnimals: LiveData<List<Animal>> = animalDao.getAllAnimals()

    @WorkerThread
    suspend fun insert(animal: Animal){
        animalDao.insert(animal)
    }
}
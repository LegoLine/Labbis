package lnr.hk.no.labbisv3

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AnimalViewModel(application: Application): AndroidViewModel(application){
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: AnimalRepository
    val allAnimals: LiveData<List<Animal>>

    init {
        val animalDao = AnimalRoomDatabase.getDatabase(application, scope).animalDao()
        repository = AnimalRepository(animalDao)
        allAnimals = repository.allAnimals
    }

    fun insert(animal: Animal) = scope.launch(Dispatchers.IO){
        repository.insert(animal)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
package lnr.hk.no.labbisv3

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface AnimalDao{

    @Query("SELECT * FROM animal_table ORDER BY animal ASC")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animal: Animal)

    @Query("DELETE FROM animal_table")
    fun deleteAll()
}
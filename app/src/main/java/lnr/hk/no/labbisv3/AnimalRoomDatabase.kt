package lnr.hk.no.labbisv3

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Animal::class], version = 1)
public abstract class AnimalRoomDatabase: RoomDatabase(){
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope): AnimalRoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalRoomDatabase::class.java,
                    "animal_database"
                ).addCallback(AnimalDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

    }
    private class AnimalDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO){
                    populateDatabase(database.animalDao())
                }
            }
        }

        fun populateDatabase(animalDao: AnimalDao){
            animalDao.deleteAll()

            var animal = Animal("Fido")
            animalDao.insert(animal)
            animal = Animal("Fred")
            animalDao.insert(animal)
        }
    }
}
package com.example.cleancode.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cleancode.db.entities.PersonajeEntity
import com.example.cleancode.db.entities.PersonajeDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(PersonajeEntity::class), version = 1, exportSchema = false)
abstract class HelperDataBase : RoomDatabase() {

    abstract fun personajeDao() : PersonajeDAO


    private class HelperDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    //something for the future
                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE : HelperDataBase? = null

        fun getDatabase(context: Context) : HelperDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HelperDataBase::class.java,
                    "helper_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
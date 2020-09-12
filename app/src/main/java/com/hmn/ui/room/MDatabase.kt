package com.hmn.ui.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CategiryEntity::class] ,version = 1, exportSchema = false
)
abstract class MDatabase : RoomDatabase() {

    abstract fun categoryDao(): Dao


    companion object {
        private var INSTANCR: MDatabase? = null

        fun getDatabase(context: Context): MDatabase {
            if (INSTANCR == null) {
                INSTANCR = Room.databaseBuilder(context, MDatabase::class.java,"category.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCR!!
        }
    }
}
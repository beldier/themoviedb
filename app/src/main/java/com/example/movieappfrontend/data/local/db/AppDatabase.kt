package com.example.movieappfrontend.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappfrontend.data.local.dao.MovieDao
import com.example.movieappfrontend.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase?= null

        fun getInstance(context: Context):AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "the_movie_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }

}
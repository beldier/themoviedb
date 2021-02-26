package com.example.movieappfrontend.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieappfrontend.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE movie.id = :id")
     fun getMovie(id: String): Movie?

    @Insert
     fun insert(movie:Movie)

    @Query("DELETE FROM movie WHERE id = :id")
     fun deleteMovie(id: String)
}
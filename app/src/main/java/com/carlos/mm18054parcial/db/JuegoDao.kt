package com.carlos.mm18054parcial.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JuegoDao {
    @Query("SELECT * FROM juego")
    fun getAll(): LiveData<List<JuegoEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(juego: JuegoEntity)
    @Update
    suspend fun update(juego: JuegoEntity)
    @Delete
    suspend fun delete(juego: JuegoEntity)
    @Query("DELETE FROM juego")
    suspend fun deleteAll()
}
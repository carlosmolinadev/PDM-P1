package com.carlos.mm18054parcial.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartidaDao {
    @Query("SELECT * FROM partida")
    fun getAll(): LiveData<List<PartidaEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(partida: PartidaEntity)
    @Update
    suspend fun update(partida: PartidaEntity)
    @Delete
    suspend fun delete(partida: PartidaEntity)
    @Query("DELETE FROM partida")
    suspend fun deleteAll()
}
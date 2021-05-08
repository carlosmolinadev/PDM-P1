package com.carlos.mm18054parcial.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class Repository(private val db: RegistroDB) {
    /***************************
     * Juego repository
     ***************************/
    val juegos: LiveData<List<JuegoEntity>> = db.juegoDao().getAll()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(juego: JuegoEntity) {
        db.juegoDao().insert(juego)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(juego: JuegoEntity) {
        db.juegoDao().update(juego)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(juego: JuegoEntity) {
        db.juegoDao().delete(juego)
    }
    /***************************
     * Partida repository
     ***************************/
    val partidas: LiveData<List<PartidaEntity>> = db.partidaDao().getAll()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(partida: PartidaEntity) {
        db.partidaDao().insert(partida)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(partida: PartidaEntity) {
        db.partidaDao().update(partida)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(partida: PartidaEntity) {
        db.partidaDao().delete(partida)
    }

}

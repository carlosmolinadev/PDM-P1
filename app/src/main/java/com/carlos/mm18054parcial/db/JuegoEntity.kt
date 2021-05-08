package com.carlos.mm18054parcial.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "juego")
data class JuegoEntity(
    @PrimaryKey(autoGenerate = true)
    val identificador: Int,
    val nombre: String,
    val descripcion: String,
    @ColumnInfo(name = "cant_minima", defaultValue = "0")
    val cantMinima: Int,
    @ColumnInfo(name = "cant_maxima", defaultValue = "0")
    val cantMaxima: Int,
    val categoria: String
)
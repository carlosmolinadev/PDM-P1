package com.carlos.mm18054parcial.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "partida",
    primaryKeys = ["identificador"],
    foreignKeys = [
        ForeignKey(
            entity = JuegoEntity::class,
            parentColumns = ["identificador"],
            childColumns = ["identificador"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class PartidaEntity(
    val identificador: Int,
    val ganador:String,
    @ColumnInfo(name = "cant_participantes")
    val cantParticipantes: Int,
)
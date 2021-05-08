package com.carlos.mm18054parcial.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        JuegoEntity::class,
        PartidaEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class RegistroDB : RoomDatabase() {
    abstract fun juegoDao(): JuegoDao
    abstract fun partidaDao(): PartidaDao

    private class DBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(db: RegistroDB) {
            // Limpiar base
            db.juegoDao().deleteAll()
            db.partidaDao().deleteAll()

            val VAnombre = listOf("Juego1","Juego2")
            val VAdescripcion = listOf("JuegoDesc1","JuegoDesc1")
            val VAcantMin = listOf(2,2)
            val VAcantMax = listOf(5, 10)
            val VAcategoria = listOf("Ping Pong", "Futbolito")

            val VPidentificador = listOf(1,2)
            val VPganador = listOf("Carlos", "Julio")
            val VPparticipantes = listOf(2,10)

            for (i in 0..1) {
                db.juegoDao().insert(
                    JuegoEntity(0,VAnombre[i], VAdescripcion[i],VAcantMin[i],VAcantMax[i],VAcategoria[i])
                )}
            for (i in 0..1) {
                db.partidaDao().insert(
                    PartidaEntity(
                        VPidentificador[i],
                        VPganador[i],
                        VPparticipantes[i],
                    )
                )}
        }}

    companion object {
        @Volatile
        private var INSTANCE: RegistroDB? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RegistroDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RegistroDB::class.java,
                    "mm18054_db"
                ).addCallback(DBCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
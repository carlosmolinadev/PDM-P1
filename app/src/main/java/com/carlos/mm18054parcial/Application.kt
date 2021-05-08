package com.carlos.mm18054parcial

import android.app.Application
import com.carlos.mm18054parcial.db.RegistroDB
import com.carlos.mm18054parcial.db.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RegistroDB.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database) }
}
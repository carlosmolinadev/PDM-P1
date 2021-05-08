package com.carlos.mm18054parcial.ui.juego

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlos.mm18054parcial.db.JuegoEntity
import com.carlos.mm18054parcial.db.Repository
import kotlinx.coroutines.launch

class JuegoViewModel(private val repository: Repository) : ViewModel() {
    val juegos: LiveData<List<JuegoEntity>> = repository.juegos
    var juegoActual: JuegoEntity? = null

    fun insert(usuario: JuegoEntity) = viewModelScope.launch {
        repository.insert(usuario)
    }
    fun update(usuario: JuegoEntity) = viewModelScope.launch {
        repository.update(usuario)
    }
    fun delete(usuario: JuegoEntity) = viewModelScope.launch {
        repository.delete(usuario)
    }
}

class JuegoViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JuegoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JuegoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.carlos.mm18054parcial.ui.partida

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlos.mm18054parcial.db.PartidaEntity
import com.carlos.mm18054parcial.db.Repository
import kotlinx.coroutines.launch

class PartidaViewModel(private val repository: Repository) : ViewModel() {
    val partida: LiveData<List<PartidaEntity>> = repository.partidas
    var partidaActual: PartidaEntity? = null

    fun insert(usuario: PartidaEntity) = viewModelScope.launch {
        repository.insert(usuario)
    }
    fun update(usuario: PartidaEntity) = viewModelScope.launch {
        repository.update(usuario)
    }
    fun delete(usuario: PartidaEntity) = viewModelScope.launch {
        repository.delete(usuario)
    }
}

class PartidaViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartidaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PartidaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
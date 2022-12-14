package com.D121201057.tugasin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.D121201057.tugasin.data.TugasDao
import com.D121201057.tugasin.data.TugasDatabase
import com.D121201057.tugasin.model.Tugas
import com.D121201057.tugasin.repository.TugasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel(application: Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Tugas>>
    val readAllDataHistory: LiveData<List<Tugas>>
    val readAllDataPentingMendesak: LiveData<List<Tugas>>
    val readAllDataTidakPentingMendesak: LiveData<List<Tugas>>
    val readAllDataPentingTidakMendesak: LiveData<List<Tugas>>
    val readAllDataTidakPentingTidakMendesak: LiveData<List<Tugas>>

    private val repository: TugasRepository
    init {
        val tugasDao=TugasDatabase.getDatabase(application).tugasDao()
        repository= TugasRepository(tugasDao)
        readAllData=repository.readAllData
        readAllDataHistory=repository.readAllDataHistory
        readAllDataPentingMendesak=repository.readAllDataPentingMendesak
        readAllDataTidakPentingMendesak=repository.readAllDataTidakPentingMendesak
        readAllDataPentingTidakMendesak=repository.readAllDataPentingTidakMendesak
        readAllDataTidakPentingTidakMendesak=repository.readAllDataTidakPentingTidakMendesak

    }
    fun addTugas (tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTugas(tugas)
        }
    }
    fun updateTugas(tugas: Tugas){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateTugas(tugas)
        }
    }
    fun deleteTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTugas(tugas)
        }
    }
}
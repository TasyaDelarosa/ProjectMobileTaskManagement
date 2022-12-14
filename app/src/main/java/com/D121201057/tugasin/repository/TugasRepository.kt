package com.D121201057.tugasin.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.D121201057.tugasin.data.TugasDao
import com.D121201057.tugasin.model.Tugas

class TugasRepository(private val tugasDao: TugasDao) {
    val readAllData: LiveData<List<Tugas>> = tugasDao.readALlData()
    val readAllDataHistory: LiveData<List<Tugas>> = tugasDao.readALlDataHistory()
    val readAllDataPentingMendesak: LiveData<List<Tugas>> = tugasDao.readALlDataPentingMendesak()
    val readAllDataTidakPentingMendesak: LiveData<List<Tugas>> = tugasDao.readALlDataTidakPentingMendesak()
    val readAllDataPentingTidakMendesak: LiveData<List<Tugas>> = tugasDao.readALlDataPentingTidakMendesak()
    val readAllDataTidakPentingTidakMendesak: LiveData<List<Tugas>> = tugasDao.readALlDataTidakPentingTidakMendesak()

    suspend fun addTugas(tugas: Tugas){
        tugasDao.tambahTugas(tugas)
    }
    suspend fun updateTugas(tugas: Tugas){
        tugasDao.updateTugas(tugas)
    }
    suspend fun deleteTugas(tugas: Tugas){
        tugasDao.deleteTugas(tugas)
    }
}
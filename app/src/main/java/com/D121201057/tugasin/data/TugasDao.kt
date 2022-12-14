package com.D121201057.tugasin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.D121201057.tugasin.model.Tugas

@Dao

interface TugasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun tambahTugas (tugas: Tugas)

    @Query("SELECT * from tabeltugas WHERE hapus='g'")
    fun readALlData():LiveData<List<Tugas>>
    @Query("SELECT * from tabeltugas WHERE hapus='y'")
    fun readALlDataHistory():LiveData<List<Tugas>>
    @Query("SELECT * from tabeltugas WHERE kategori='Penting Mendesak'")
    fun readALlDataPentingMendesak():LiveData<List<Tugas>>
    @Query("SELECT * from tabeltugas WHERE kategori='Tidak Penting Mendesak'")
    fun readALlDataTidakPentingMendesak():LiveData<List<Tugas>>
    @Query("SELECT * from tabeltugas WHERE kategori='Penting Tidak Mendesak'")
    fun readALlDataPentingTidakMendesak():LiveData<List<Tugas>>
    @Query("SELECT * from tabeltugas WHERE kategori='Tidak Penting Tidak Mendesak'")
    fun readALlDataTidakPentingTidakMendesak():LiveData<List<Tugas>>

    @Update
    suspend fun updateTugas(tugas:Tugas)

    @Delete
    suspend fun deleteTugas(tugas: Tugas)
}
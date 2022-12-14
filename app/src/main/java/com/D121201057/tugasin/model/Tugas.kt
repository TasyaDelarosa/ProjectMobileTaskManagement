package com.D121201057.tugasin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabeltugas")

data class Tugas (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val judul:String,
    val kategori:String,
    val status:String,
    val deskripsi:String,
    var hapus:String
):Parcelable
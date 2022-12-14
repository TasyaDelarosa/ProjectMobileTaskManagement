package com.D121201057.tugasin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.D121201057.tugasin.model.Tugas
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Tugas::class], version = 1, exportSchema = false)
abstract class TugasDatabase: RoomDatabase() {
    abstract fun tugasDao():TugasDao
    companion object {
        @Volatile
        private var INSTANCE:TugasDatabase?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase (context: Context):TugasDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TugasDatabase::class.java,
                    "listtugas"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}
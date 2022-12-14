package com.D121201057.tugasin.activities

import android.R
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.D121201057.tugasin.databinding.ActivityAddTaskBinding
import com.D121201057.tugasin.model.Tugas
import com.D121201057.tugasin.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var kategoriTugasAdapter: ArrayAdapter<String>
    private lateinit var statusTugasAdapter: ArrayAdapter<String>
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        val kategoriTugas = arrayOf("Penting Mendesak", "Tidak Penting Mendesak", "Penting Tidak Mendesak", "Tidak Penting Tidak Mendesak")
        val statusTugas = arrayOf("To Do", "In Progress", "Completed")

        kategoriTugasAdapter = ArrayAdapter(this, R.layout.simple_list_item_1,kategoriTugas)
        statusTugasAdapter = ArrayAdapter(this, R.layout.simple_list_item_1,statusTugas)
        binding.dropdownMenutugas.setAdapter(kategoriTugasAdapter)
        binding.dropdownMenustatus.setAdapter(statusTugasAdapter)
        binding.batal.setOnClickListener {
            finish()
        }
        binding.simpan.setOnClickListener {
            val judultugas=binding.judul.text.toString()
            val kategoritugas=binding.dropdownMenutugas.text.toString()
            val statustugas=binding.dropdownMenustatus.text.toString()
            val deskripsi=binding.deskripsi.text.toString()

            if (judultugas.isEmpty()){
                binding.judulerror.isErrorEnabled = true
                binding.judulerror.error = "Masukkan judul"
                return@setOnClickListener
            }
            if (kategoritugas.isEmpty()){
                binding.kategoriTugas.isErrorEnabled = true
                binding.kategoriTugas.error = "Pilih kategori"
                return@setOnClickListener
            }
            if(statustugas.isEmpty()){
                binding.kategoriStatus.isErrorEnabled = true
                binding.kategoriStatus.error = "Pilih status"
                return@setOnClickListener
            }
            if(deskripsi.isEmpty()){
                binding.deskripsierror.isErrorEnabled = true
                binding.deskripsierror.error = "Masukkan deskripsi"
                return@setOnClickListener
            }

            lifecycleScope.launch{
                val tugas = Tugas(0,judultugas,kategoritugas,statustugas,deskripsi,"g")
                tugasViewModel.addTugas(tugas)
                finish()
            }
        }
        errorRemoving()
    }
    private fun errorRemoving(){
        binding.apply {
            judul.setOnClickListener{
                judulerror.isErrorEnabled  = false
            }
            dropdownMenutugas.setOnClickListener {
                kategoriTugas.isErrorEnabled = false
            }
            dropdownMenustatus.setOnClickListener {
                kategoriStatus.isErrorEnabled = false
            }
            deskripsi.setOnClickListener {
                deskripsierror.isErrorEnabled = false
            }

        }
    }
}
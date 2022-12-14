package com.D121201057.tugasin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201057.tugasin.R
import com.D121201057.tugasin.adapter.TugasAdapter
import com.D121201057.tugasin.databinding.FragmentEditBinding
import com.D121201057.tugasin.databinding.FragmentListBinding
import com.D121201057.tugasin.model.Tugas
import com.D121201057.tugasin.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel

    private val args by navArgs<EditFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentEditBinding.inflate(inflater,container,false)
        val view=binding.root



        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.judul.setText(args.currentTugas.judul)
        binding.dropdownMenutugas.setText(args.currentTugas.kategori)
        binding.dropdownMenustatus.setText(args.currentTugas.status)
        binding.dropdownMenutugas.setAdapter(ArrayAdapter.createFromResource(requireContext(),R.array.kategoriTugas,android.R.layout.simple_list_item_1))
        binding.dropdownMenustatus.setAdapter(ArrayAdapter.createFromResource(requireContext(),R.array.statusTugas,android.R.layout.simple_list_item_1))
        binding.deskripsi.setText(args.currentTugas.deskripsi)

        binding.batal.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_listFragment)
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
                val tugas = Tugas(args.currentTugas.id,judultugas,kategoritugas,statustugas,deskripsi,"g")
                tugasViewModel.updateTugas(tugas)
                Toast.makeText(requireContext(),"Berhasil di edit",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editFragment_to_listFragment)
            }
        }









        return view
    }
}
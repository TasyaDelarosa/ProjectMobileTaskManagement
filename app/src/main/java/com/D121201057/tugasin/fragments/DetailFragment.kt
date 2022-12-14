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
import com.D121201057.tugasin.R
import com.D121201057.tugasin.databinding.FragmentDetailBinding
import com.D121201057.tugasin.databinding.FragmentEditBinding
import com.D121201057.tugasin.model.Tugas
import com.D121201057.tugasin.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel

    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(inflater,container,false)
        val view=binding.root


        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]

        binding.judul.setText(args.currentTugas.judul)
        binding.dropdownMenutugas.setText(args.currentTugas.kategori)
        binding.dropdownMenustatus.setText(args.currentTugas.status)
        binding.deskripsi.setText(args.currentTugas.deskripsi)










        return view
    }
}
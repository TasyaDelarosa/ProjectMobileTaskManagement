package com.D121201057.tugasin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.D121201057.tugasin.R
import com.D121201057.tugasin.adapter.HistoryAdapter
import com.D121201057.tugasin.adapter.TugasAdapter
import com.D121201057.tugasin.databinding.FragmentHistoryBinding
import com.D121201057.tugasin.databinding.FragmentListBinding
import com.D121201057.tugasin.viewmodel.TugasViewModel

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHistoryBinding.inflate(inflater,container,false)
        val view=binding.root


        val adapter= HistoryAdapter()
        binding.recyclelist.adapter= adapter
        binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
        tugasViewModel.readAllDataHistory.observe(viewLifecycleOwner){tugas->
            adapter.tambahdata(tugas)
            if (tugas.size==0){
                binding.historyempty.visibility = View.VISIBLE
                binding.recyclelist.visibility = View.GONE
            }else{
                binding.historyempty.visibility = View.GONE
                binding.recyclelist.visibility = View.VISIBLE
            }

        }



        return view
    }

}
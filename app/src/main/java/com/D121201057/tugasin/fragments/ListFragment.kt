package com.D121201057.tugasin.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.D121201057.tugasin.R
import com.D121201057.tugasin.adapter.TugasAdapter
import com.D121201057.tugasin.databinding.FragmentListBinding
import com.D121201057.tugasin.model.Tugas
import com.D121201057.tugasin.viewmodel.TugasViewModel

class ListFragment : Fragment() {
    private var _binding:FragmentListBinding?=null
    private val binding get() = _binding!!
    private lateinit var kategoriTugasAdapter: ArrayAdapter<String>
    private lateinit var tugasViewModel: TugasViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentListBinding.inflate(inflater,container,false)
        val view=binding.root

        val kategoriTugas = arrayOf("Semua Kategori","Penting Mendesak", "Tidak Penting Mendesak", "Penting Tidak Mendesak", "Tidak Penting Tidak Mendesak")
        kategoriTugasAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,kategoriTugas)
        binding.dropdownMenu.setText("Semua Kategori")
        binding.dropdownMenu.setAdapter(kategoriTugasAdapter)
        val adapter=TugasAdapter()
        binding.recyclelist.adapter= adapter
        binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
        tugasViewModel.readAllData.observe(viewLifecycleOwner){tugas->
            adapter.tambahdata(tugas)
            notask(tugas.size)
            binding.jumlahlist.text = tugas.size.toString()
        }
        binding.linekategori.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.judul))

        binding.dropdownMenu.setOnItemClickListener { adapterView, _, i, _ ->

            when (i){

                0->{
                    val adapter=TugasAdapter()
                    binding.recyclelist.adapter= adapter
                    binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
                    tugasViewModel.readAllData.observe(viewLifecycleOwner){tugas->
                        adapter.tambahdata(tugas)
                        notask(tugas.size)
                        binding.jumlahlist.text = tugas.size.toString()
                    }
                    binding.linekategori.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.black))
                }
                1->{
                    val adapter=TugasAdapter()
                    binding.recyclelist.adapter= adapter
                    binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
                    tugasViewModel.readAllDataPentingMendesak.observe(viewLifecycleOwner){tugas->
                        adapter.tambahdata(tugas)
                        notask(tugas.size)
                        binding.jumlahlist.text = tugas.size.toString()
                    }
                    binding.linekategori.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.pentingmendesak))
                }
                2->{
                    val adapter=TugasAdapter()
                    binding.recyclelist.adapter= adapter
                    binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
                    tugasViewModel.readAllDataTidakPentingMendesak.observe(viewLifecycleOwner){tugas->
                        adapter.tambahdata(tugas)
                        notask(tugas.size)
                        binding.jumlahlist.text = tugas.size.toString()
                    }
                    binding.linekategori.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.tpentingmendesak))
                }
                3->{
                    val adapter=TugasAdapter()
                    binding.recyclelist.adapter= adapter
                    binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
                    tugasViewModel.readAllDataPentingTidakMendesak.observe(viewLifecycleOwner){tugas->
                        adapter.tambahdata(tugas)
                        notask(tugas.size)
                        binding.jumlahlist.text = tugas.size.toString()
                    }
                    binding.linekategori.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.pentingtidakmendesak))
                }
                4->{
                    val adapter=TugasAdapter()
                    binding.recyclelist.adapter= adapter
                    binding.recyclelist.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    tugasViewModel= ViewModelProvider(this)[TugasViewModel::class.java]
                    tugasViewModel.readAllDataTidakPentingTidakMendesak.observe(viewLifecycleOwner){tugas->
                        adapter.tambahdata(tugas)
                        notask(tugas.size)
                        binding.jumlahlist.text = tugas.size.toString()
                    }
                    binding.linekategori.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.tidakpentingtidakmendesak))
                }
            }

        }



        return view
    }

    private fun notask(size:Int){
        if (size==0){
            binding.notask.visibility = View.VISIBLE
            binding.recyclelist.visibility = View.GONE
        }else{
            binding.notask.visibility = View.GONE
            binding.recyclelist.visibility = View.VISIBLE
        }
    }

}
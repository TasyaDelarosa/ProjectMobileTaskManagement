package com.D121201057.tugasin.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.D121201057.tugasin.R
import com.D121201057.tugasin.fragments.ListFragmentDirections
import com.D121201057.tugasin.model.Tugas
import com.D121201057.tugasin.viewmodel.TugasViewModel

class TugasAdapter : RecyclerView.Adapter<TugasAdapter.TugasViewHolder>(){
    private var listtugas = emptyList<Tugas>()
    private lateinit var tugasViewModel: TugasViewModel

    private var context :Context ?= null
    class TugasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var judul:TextView= itemView.findViewById(R.id.judul)
        var isi:TextView= itemView.findViewById(R.id.isi)
        var status:TextView= itemView.findViewById(R.id.status)
        var kategori:TextView= itemView.findViewById(R.id.kategoritugas)
        var opsi:ImageView = itemView.findViewById(R.id.opsi)
        var klik:CoordinatorLayout = itemView.findViewById(R.id.klik)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        return TugasViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterlist,parent,false))
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val currentitem = listtugas[position]
        holder.judul.text= currentitem.judul
        holder.isi.text= currentitem.deskripsi
        holder.status.text= currentitem.status
        holder.kategori.text= currentitem.kategori

        if (currentitem.status=="To Do"){
            holder.status.setTextColor(context!!.resources.getColor(R.color.todo))
            holder.status.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.bgtodo))
        }else if(currentitem.status=="In Progress"){
            holder.status.setTextColor(context!!.resources.getColor(R.color.inprogress))
            holder.status.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.bginprogress))
        }else{
            holder.status.setTextColor(context!!.resources.getColor(R.color.complete))
            holder.status.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.bgcomplete))
        }

        if (currentitem.kategori=="Penting Mendesak"){
            holder.kategori.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.pentingmendesak))
        }else if (currentitem.kategori=="Tidak Penting Mendesak"){
            holder.kategori.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.tpentingmendesak))
        }else if (currentitem.kategori=="Penting Tidak Mendesak"){
            holder.kategori.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.pentingtidakmendesak))
        }else{
            holder.kategori.backgroundTintList= ColorStateList.valueOf(context!!.resources.getColor(R.color.tidakpentingtidakmendesak))
        }
        holder.opsi.setOnClickListener {
            opsilain(it,position,holder)
        }
        holder.klik.setOnClickListener {
            holder.itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(listtugas[position]))
        }
    }

    override fun getItemCount(): Int {
        return listtugas.size
    }

    fun tambahdata(tugas: List<Tugas>){
        this.listtugas= tugas
        notifyDataSetChanged()
    }

    private fun opsilain(view:View,position: Int,holder: TugasViewHolder){
        val popup = PopupMenu(context,view)
        popup.inflate(R.menu.popuptitik)
        popup.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.edit->{
                    val action = ListFragmentDirections.actionListFragmentToEditFragment(listtugas[position])
                    holder.itemView.findNavController().navigate(action)
                    true
                }
                R.id.hapus->{
                    val tugas = Tugas(listtugas[position].id,listtugas[position].judul,listtugas[position].kategori,listtugas[position].status,listtugas[position].deskripsi,"y")
                    tugasViewModel.updateTugas(tugas)
                    Toast.makeText(context,"Berhasil dihapus",Toast.LENGTH_SHORT).show()
                    true
                }else->true
            }
        }
        popup.show()
        val popupp = PopupMenu::class.java.getDeclaredField("mPopup")
        popupp.isAccessible = true
        val menu  = popupp.get(popup)
        menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)
    }
}
package com.D121201057.tugasin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.D121201057.tugasin.R
import com.D121201057.tugasin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tambahtugas.setOnClickListener {
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }

        val navcontroller = findNavController(R.id.fragment)
        binding.appbar.setupWithNavController(navcontroller)

        navcontroller.addOnDestinationChangedListener{_,destination,_->
            if(destination.id==R.id.detailFragment||destination.id==R.id.editFragment){
                binding.bottombar.visibility = View.GONE
                binding.tambahtugas.visibility = View.GONE
            }else{
                binding.bottombar.visibility = View.VISIBLE
                binding.tambahtugas.visibility = View.VISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController((R.id.fragment))
        return navController.navigateUp()|| super.onSupportNavigateUp()
    }
}
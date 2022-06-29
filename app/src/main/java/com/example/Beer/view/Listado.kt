package com.example.Beer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.Beer.R
import com.example.Beer.view.fragments.RecyclerListFragment


class Listado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)

        setupFragment()
    }
    private fun setupFragment(){
        val fragment = RecyclerListFragment.newInstance()
        val fragmentManager: FragmentManager= supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }
}
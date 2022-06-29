package com.example.Beer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Beer.R
import com.example.Beer.model.RecyclerList
import com.example.Beer.view.adapter.ItemsAdapter
import com.example.Beer.viewModel.ActivityViewModel

class RecyclerListFragment : Fragment() {
    lateinit var recyclerAdapter : ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View){
       val recycler= view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)
        recyclerAdapter = ItemsAdapter()
        recycler.adapter = recyclerAdapter
    }

    private fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if (it != null){
                recyclerAdapter.setUpdateData(it.items)
            }else{
                Toast.makeText(activity, "Error de datos", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        fun newInstance() = RecyclerListFragment()

    }
}
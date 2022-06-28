package com.example.Beer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Beer.model.BeerDataModel
import com.example.Beer.view.adapter.ItemsAdapter
import com.example.Beer.viewModel.RecyclerBeerViewModel
import com.example.Beer.R
import com.example.Beer.databinding.FragmentBeerList2Binding

class PokemonListFragment : Fragment(), ClickListener {
    lateinit var viewModel: RecyclerBeerViewModel
    lateinit var binding: FragmentBeerList2Binding
    private var mAdapter: ItemsAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it).get(RecyclerBeerViewModel::class.java)
            }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beer_list2, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // inicializa recyclerview
        mAdapter = ItemsAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = mAdapter

        //observador de la lista
        viewModel.listState.observe(viewLifecycleOwner) {
            mAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }

        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }

        //mAdapter?.setItems(list)
        viewModel.fetchBeerData()

    }

    override fun itemSelect(data: BeerDataModel) {
        viewModel.setItemSelection(data)

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(android.R.id.content, BeerDetailFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }


}

interface ClickListener {
    fun itemSelect(data: BeerDataModel)
}
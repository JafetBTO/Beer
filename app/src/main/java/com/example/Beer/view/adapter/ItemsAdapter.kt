package com.example.Beer.view.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.Beer.R
import com.example.Beer.databinding.BeerRowBinding
import com.example.Beer.model.BeerDataModel
import com.example.Beer.view.fragments.ClickListener
import com.example.Beer.view.viewholder.ItemViewHolder

class ItemsAdapter(private val listener: ClickListener): RecyclerView.Adapter<ItemViewHolder>() {
    private val resource = R.layout.beer_row
    lateinit var context: Context
    private val itemList = mutableListOf<BeerDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: BeerRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.itemSelect(itemList[position])
        }
    }

    fun setItems(list: MutableList<BeerDataModel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}
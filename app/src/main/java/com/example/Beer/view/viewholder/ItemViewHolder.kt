package com.example.Beer.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Beer.databinding.BeerRowBinding
import com.example.Beer.model.BeerDataModel

class ItemViewHolder(binding: BeerRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: BeerRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: BeerDataModel) {
        binding?.let { view->
            view.name = model.name

            Glide.with(view.root.context).load(model.img).into(view.imgCharacter)
        }
    }
}
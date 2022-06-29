package com.example.Beer.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Beer.R
import com.example.Beer.model.RecyclerData
import com.squareup.picasso.Picasso

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setUpdateData(items : ArrayList<RecyclerData>){
        this.items = items
        notifyDataSetChanged()
    }


    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){

        val image = view.findViewById<ImageView>(R.id.img_character)
        val txt_name = view.findViewById<TextView>(R.id.txt_name)
        val txt_desp = view.findViewById<TextView>(R.id.txt_description)


        fun bind(data:RecyclerData){
            txt_name.text = data.name
            txt_desp.text = data.description

            val img = data.image.image_url

            Picasso.get()
                .load(img)
                .into(image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_row, parent, false)

        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
holder.bind(items.get(position))
    }
}
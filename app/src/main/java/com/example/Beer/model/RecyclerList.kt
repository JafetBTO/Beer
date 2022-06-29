package com.example.Beer.model

data class RecyclerList (val items:ArrayList<RecyclerData>)
data class RecyclerData(val name: String, val description:String, val image:image)
data class image(val image_url:String)
package com.example.Beer.model

data class BeerResponse(
    val beer: MutableList<BeerDataModel> = mutableListOf()
)

data class BeerDataModel (
    val id: Long,
    val name: String = "",
    val img: String
)
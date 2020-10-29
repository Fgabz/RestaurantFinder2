package com.fanilo.entity.restaurant

import com.fanilo.entity.Coordinate

data class Restaurant(
    val id: String,
    val location: Coordinate,
    val category: List<Category>
)

data class Category(
    val name: String
)
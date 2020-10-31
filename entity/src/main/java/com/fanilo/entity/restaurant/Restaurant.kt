package com.fanilo.entity.restaurant

import com.fanilo.entity.Coordinate

data class Restaurant(
    val id: String,
    val location: Coordinate,
    val category: List<Category>,
    val name: String
)

data class Category(
    val name: String
)
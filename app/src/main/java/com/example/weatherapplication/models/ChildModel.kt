package com.example.weatherapplication.models

/**Stores data for Item for recycler-view present in card**/

data class ChildModel(
    var dt: Long,
    var min: Float,
    var max: Float,
    var icon: String
)

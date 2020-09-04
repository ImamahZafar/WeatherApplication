package com.example.weatherapplication.models

data class HourlyForecastModel (

    var lat: Float,
    var lon: Float,
    var timezone: String,
    var timezone_offset: Long,
    var hourly: List<Hourly> = listOf<Hourly>()
)
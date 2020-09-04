package com.example.weatherapplication.models

data class Hourly (
    var dt: Long,
    var humidity: Int,
    var pressure: Float,
    var temp: Float,
    var feels_like: Float,
    var dew_point: Float,
    var clouds: Int,
    var visibility : Long,
    var wind_speed: Double,
    var wind_deg: Double,
    var weather: List<Weather> = listOf<Weather>(),
    var pop : Float
)
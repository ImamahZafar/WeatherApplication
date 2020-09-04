package com.example.weatherapplication.models

data class Daily(
    var dt: Long,
    var sunrise: Long,
    var sunset: Long,
    var temp: Temp,
    var feels_like: Feels_Like,
    var pressure: Int,
    var humidity: Int,
    var dew_point: Float,
    var wind_speed: Double,
    var weather: List<Weather> = listOf<Weather>(),
    var uvi: Double,
    var clouds: Int,
    var wind_deg: Double,
    var pop: Float

)
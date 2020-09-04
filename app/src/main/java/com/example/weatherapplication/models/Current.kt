package com.example.weatherapplication.models
import com.example.weatherapplication.models.HourlyForecastModel

data class Current (
    var dt: Long,
    var sunrise: Long,
    var sunset: Long,
    var humidity: Int,
    var pressure: Float,
    var temp: Float,
    var feels_like: Float,
    var dew_point: Float,
    var uvi: Double,
    var clouds: Int,
    var visibility : Long,
    var wind_speed: Double,
    var wind_deg: Double,
    var weather: List<Weather> = listOf<Weather>()

    )
